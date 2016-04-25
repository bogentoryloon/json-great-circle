package ie.jtc.nearby;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import ie.jtc.nearby.beans.LongitudeAndLatitude;
import ie.jtc.nearby.beans.PersonAndLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ie.jtc.nearby.services.LocationService;
import ie.jtc.nearby.services.PeopleDataService;

/**
 * command line interface to the intercom.io 2nd exercise,
 * parsing some json, filters it based on great circle distances, then outputs to stdout.
 * @author John
 *
 */
@SpringBootApplication
public class RunApp {
	Logger logger = LoggerFactory.getLogger(RunApp.class);
	@Autowired 
	PeopleDataService peopleDataService;
	@Autowired
	LocationService locationService;
	public static void main(String[] args) throws MalformedURLException, IOException {
		SpringApplication.run(RunApp.class, args);
	}
	

	/**
	 * Spring (not lombok) dynamic properties,
	 * are contained in application.properties
	 */
	@Value("${nearby.people.url}")
	private String url ;
	@Value("${nearby.base.coords}")
	private String baseLongLat;
	@Value("${nearby.radius_km}")
	private int cutoffDistance;
	
	@PostConstruct
	void run() throws MalformedURLException, IOException{
	
		List<PersonAndLocation> customerList = peopleDataService.getFromUrl(new URL(url));
		LongitudeAndLatitude base=new LongitudeAndLatitude(baseLongLat);
		// return a subset of the list of people based on distance from a location
		List<PersonAndLocation> selectFew = peopleDataService.remove(customerList,new PeopleDataService.Evaluator() {						
			@Override
			public boolean include(PersonAndLocation candidate) {
				double kmsAway=locationService.distanceInKilometres(base, candidate.getLocation());
				logger.debug(base+" to "+candidate+" is "+kmsAway+" away");
				return ( kmsAway <= cutoffDistance );				
			}
		});
		/**
		 * output the simple representation of PersonAndData ordered by user_id
		 */
		peopleDataService.output(System.out,selectFew,new Comparator<PersonAndLocation>() {
			@Override
			public int compare(PersonAndLocation o1, PersonAndLocation o2) {
				String u1 = o1.getUserId();
				String u2 = o2.getUserId();
				if (u1 == null) {
					if (u2 == null) {
						return 0;
					} else {
						return -1;
					}
				} else if (u2 == null) {
					return 1;
				} else {
					return new Integer(u1).compareTo(new Integer(u2));
				}
			}
		});
				
		
	}


}
