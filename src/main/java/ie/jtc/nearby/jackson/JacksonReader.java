package ie.jtc.nearby.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import ie.jtc.nearby.beans.LongitudeAndLatitude;
import ie.jtc.nearby.beans.PersonAndLocation;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class JacksonReader {	
	static JsonFactory factory = new JsonFactory();		
	public List<PersonAndLocation> getJsonPeople(URL url) throws JsonParseException, IOException{
	List<PersonAndLocation> dataSet=new ArrayList<PersonAndLocation>();
	ObjectMapper mapper = new ObjectMapper();
	
	JsonParser jsonParser = factory.createParser(url);
	MappingIterator<PersonAndLocationDTO> jsonIter= mapper.readValues( jsonParser,PersonAndLocationDTO.class);
	while(jsonIter.hasNext()){	
		PersonAndLocationDTO dto=jsonIter.nextValue();
		/**
		 * decant from object tightly coupled to particular json representation
		 * to neutral version  
		 */
		PersonAndLocation person = new PersonAndLocation();
		LongitudeAndLatitude location = new LongitudeAndLatitude( );
		
		location.setLongitude(dto.getLongitude());
		location.setLatitude(dto.getLatitude());
		person.setLocation(location);
		person.setName(dto.getName());
		person.setUserId(dto.getUser_id());
		log.debug(dto.toString()+"->"+person.toString());
		
		dataSet.add(person);
	}
	return dataSet;
	}
}