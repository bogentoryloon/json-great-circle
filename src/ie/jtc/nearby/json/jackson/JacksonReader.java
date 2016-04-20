package ie.jtc.nearby.json.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import ie.jtc.nearby.json.PersonAndLocation;

public class JacksonReader {
	static JsonFactory factory = new JsonFactory();
	static Logger logger = Logger.getLogger(JacksonReader.class);
	public static List<PersonAndLocation> getJsonPeople(URL url) throws JsonParseException, IOException{
	List<PersonAndLocation> dataSet=new ArrayList<PersonAndLocation>();
	ObjectMapper mapper = new ObjectMapper();
	
	JsonParser jsonParser = factory.createParser(url);
	MappingIterator<PersonAndLocation> jsonIter= mapper.readValues( jsonParser,PersonAndLocation.class);
	while(jsonIter.hasNext()){		
		dataSet.add(jsonIter.nextValue() );
	}
	return dataSet;
	}
}
