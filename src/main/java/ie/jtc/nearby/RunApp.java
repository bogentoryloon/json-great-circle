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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ie.jtc.nearby.services.LocationService;
import ie.jtc.nearby.services.PeopleDataService;

@SpringBootApplication
public class RunApp {
	Logger logger = LoggerFactory.getLogger(RunApp.class);
	@Autowired 
	PeopleDataService peopleDataService;
	@Autowired
	LocationService locationService;
	public static void main(String[] args) throws MalformedURLException, IOException {
		SpringApplication.run(RunApp.class, args);
		//new RunApp().run();
	}
	private int cutoffDistance=100;

	@PostConstruct
	void run() throws MalformedURLException, IOException{
		String url = "https://gist.github.com/brianw/19896c50afa89ad4dec3/raw/6c11047887a03483c50017c1d451667fd62a53ca/gistfile1.txt";	
		String baseLongLat="-6.2592576,53.3381985";
		List<PersonAndLocation> customerList = peopleDataService.getFromUrl(new URL(url));
		LongitudeAndLatitude base=new LongitudeAndLatitude(baseLongLat);
		List<PersonAndLocation> selectFew = peopleDataService.remove(customerList,new PeopleDataService.Evaluator() {						
			@Override
			public boolean include(PersonAndLocation candidate) {
				double kmsAway=locationService.distanceInKilometres(base, candidate.getLocation());
				logger.debug(base+" to "+candidate+" is "+kmsAway+" away");
				return ( kmsAway <= cutoffDistance );				
			}
		});
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
