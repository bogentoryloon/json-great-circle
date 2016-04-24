package ie.jtc.nearby.services;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;


import ie.jtc.nearby.beans.PersonAndLocation;
@Service
public interface PeopleDataService {
	interface Evaluator{
		boolean include(PersonAndLocation candidate);
	}
	public  List<PersonAndLocation> getFromUrl(URL url) throws IOException;
	public  List<PersonAndLocation> remove(List<PersonAndLocation> inputList,
			Evaluator criterion);
	public void output(PrintStream out, List<PersonAndLocation> peopleList,
			Comparator<PersonAndLocation> sorter);

}
