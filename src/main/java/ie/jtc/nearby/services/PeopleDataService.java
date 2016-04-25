package ie.jtc.nearby.services;


import java.io.PrintStream;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;


import ie.jtc.nearby.beans.PersonAndLocation;
/**
 * defines operations on a list of PersonAndLocation objects 
 * @author John
 *
 */
@Service
public interface PeopleDataService {
	/**
	 * populate from a JSON source, having multiple lines of the form:
	 * {"latitude": "54.0894797", "user_id": 8, "name": "Eoin Ahearn", "longitude": "-6.18671"}
	 * @param url
	 * @return
	 */
	public  List<PersonAndLocation> getFromUrl(URL url);
	/**
	 * return a subset of list defined by arbitrary Evaluator criteria.
	 * @param inputList
	 * @param criterion
	 * @return
	 */
	public  List<PersonAndLocation> remove(List<PersonAndLocation> inputList,
			Evaluator criterion);
	interface Evaluator{
		boolean include(PersonAndLocation candidate);
	}
	/**
	 * output simple text represent summary of each item in list,
	 * optionally ordered
	 * @param out
	 * @param peopleList
	 * @param sorter
	 */
	public void output(PrintStream out, List<PersonAndLocation> peopleList,
			Comparator<PersonAndLocation> sorter);

}
