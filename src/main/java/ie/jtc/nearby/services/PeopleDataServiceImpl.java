package ie.jtc.nearby.services;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.jtc.nearby.beans.PersonAndLocation;
import ie.jtc.nearby.jackson.JacksonReader;
import lombok.extern.java.Log;
/**
 * implements operations on a list of PersonAndLocation objects
 * @author John
 *
 */
@Service
@Log
public class PeopleDataServiceImpl implements PeopleDataService {
	
	@Autowired 
	JacksonReader jacksonReader;

	/**
	 * we populate the list by delegating to our
	 * com.fasterxml.jackson JSON reader
	 */
	@Override
	public List<PersonAndLocation> getFromUrl(URL url)  {		
		return jacksonReader.getJsonPeople(url);
	}

	@Override
	public List<PersonAndLocation> remove(List<PersonAndLocation> inputList, Evaluator criterion) {
		List<PersonAndLocation> remainder = new ArrayList<PersonAndLocation>();
		for (PersonAndLocation person : inputList) {
			if ( criterion.include(person)){
				remainder.add(person);
			}
		}
		return remainder;
	}

	@Override
	public void output(PrintStream out, List<PersonAndLocation> peopleList,
			Comparator<PersonAndLocation> sorter) {
		// output will be unsorted if sorter is null
		if( sorter != null){
			Collections.sort(peopleList, sorter);
		}
		for (PersonAndLocation person : peopleList) {
			/**
			 * simple output, NB done by hand to be slightly more readable
			 * than the default toString
			 */
			out.println(person.getName()+"(user id "+person.getUserId()+")");
		}
		
	}

}
