package ie.jtc.nearby.jackson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * map a json stream to our intermediate DTO class then transform into the
	 * application PersonAndLocation we catch and log exceptions and return
	 * empty or partial data sets
	 * 
	 * @param url
	 * @return
	 */
	public List<PersonAndLocation> getJsonPeople(URL url) {
		List<PersonAndLocation> dataSet = new ArrayList<PersonAndLocation>();
		ObjectMapper mapper = new ObjectMapper();
		if (url != null) {
			JsonParser jsonParser = null;
			try {
				jsonParser = factory.createParser(url);
			} catch (IOException e) {
				/**
				 * not sure what failure means here, presumably a bad url
				 */
				log.warn("bad url:" + url + "," + e.getMessage());
			}

			MappingIterator<PersonAndLocationDTO> jsonIter = null;
			try {
				jsonIter = mapper.readValues(jsonParser, PersonAndLocationDTO.class);
			} catch (IOException e) {
				log.warn("failed to:" + url + "," + e.getMessage());
			}
			int line = 0;
			while (jsonIter.hasNext()) {
				PersonAndLocationDTO dto = null;
				try {
					++line;
					dto = jsonIter.nextValue();
				} catch (IOException e) {
					log.warn("failed at line " + line + " of " + url + "," + e.getMessage());
					continue;
				}
				/**
				 * decant from object tightly coupled to particular json
				 * representation to neutral version
				 */
				PersonAndLocation person = new PersonAndLocation();
				LongitudeAndLatitude location = new LongitudeAndLatitude();

				location.setLongitude(dto.getLongitude());
				location.setLatitude(dto.getLatitude());
				person.setLocation(location);
				person.setName(dto.getName());
				person.setUserId(dto.getUser_id());
				log.debug(dto.toString() + "->" + person.toString());

				dataSet.add(person);
			}
		}
		return dataSet;
	}
}