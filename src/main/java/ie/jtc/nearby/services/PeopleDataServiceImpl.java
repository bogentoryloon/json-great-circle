package ie.jtc.nearby.services;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.jtc.nearby.beans.PersonAndLocation;
import ie.jtc.nearby.jackson.JacksonReader;
@Service
public class PeopleDataServiceImpl implements PeopleDataService {
	static Logger logger = LoggerFactory.getLogger(PeopleDataService.class);
	@Autowired 
	JacksonReader jacksonReader;

	@Override
	public List<PersonAndLocation> getFromUrl(URL url) throws IOException {
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
		// output will be unsorted
		if( sorter != null){
			Collections.sort(peopleList, sorter);
		}
		for (PersonAndLocation person : peopleList) {
			out.println(person.toString());
		}
		
	}

}
