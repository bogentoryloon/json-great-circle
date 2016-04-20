package ie.jtc.nearby.json;

import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.log4j.Logger;

import ie.jtc.nearby.json.jackson.JacksonReader;

public class PeopleData {

	private static Logger logger = Logger.getLogger(PeopleData.class);

	public static List<PersonAndLocation> getFromUrl(URL url) throws MalformedURLException, IOException {

		return JacksonReader.getJsonPeople(url);
	}

	/**
	 * dump out the list of people
	 * 
	 * @param out
	 * @param customerList
	 * @param maxpeople
	 * @param base 
	 */
	public static void output(PrintStream out, List<PersonAndLocation> customerList, int maxpeople, LongLat base) {

		Collections.sort(customerList, new Comparator<PersonAndLocation>() {
			@Override
			public int compare(PersonAndLocation o1, PersonAndLocation o2) {
				String u1 = o1.getUser_id();
				String u2 = o2.getUser_id();
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
		for (PersonAndLocation person : customerList) {
			double kms=LocationService.distTanceInKilometres(base, person.getLocation());
			out.println(person.toString() + " is only "+kms+" away");
		}

	}

	public static List<PersonAndLocation> removeTooManyKmAway(List<PersonAndLocation> customerList, LongLat base,
			int kmAway) {
		List<PersonAndLocation> remainder = new ArrayList<PersonAndLocation>();
		for (PersonAndLocation person : customerList) {
			LongLat personLoc=person.getLocation();
			double kms=LocationService.distTanceInKilometres(base, personLoc);
			logger.debug(base+" to "+personLoc+" is "+kms+" away");
			if ( kms < kmAway) {
				remainder.add(person);
			}
		}
		return remainder;
	}

}
