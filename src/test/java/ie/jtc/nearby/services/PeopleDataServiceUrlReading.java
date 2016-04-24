package ie.jtc.nearby.services;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEventVO;
import ch.qos.logback.core.Appender;
import ie.jtc.nearby.beans.PersonAndLocation;
import ie.jtc.nearby.jackson.JacksonReader;
import lombok.extern.java.Log;



/**
 * Some simple tests of PeopleDataService data reading
 * All based on test files which are in test/resources 
 * @author John
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {PeopleDataServiceImpl.class,JacksonReader.class})
@Log
public class PeopleDataServiceUrlReading extends PeopleDataServiceBase {

	@Test
	public void testOriginalFile() throws IOException{
		URL url = super.fileToURL("original.txt");
		List<PersonAndLocation> people = peopleService.getFromUrl(url);
		assertEquals(32,people.size() );
	}
	@Test 
	public void testBadUrl() throws MalformedURLException{
		String badUrlString ="http://zzz.yyy.aaa";
		URL badUrl=new URL(badUrlString);
		List<PersonAndLocation> people = peopleService.getFromUrl(badUrl);
		assertEquals(0,people.size() );
		/**
		 * check we got a warning
		 */
        Mockito.verify(mockAppender).doAppend(captorLoggingEvent.capture());
        //
        final ch.qos.logback.classic.spi.LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        assertEquals( Level.WARN,loggingEvent.getLevel());
        assertThat(loggingEvent.getMessage(),CoreMatchers.containsString("bad url:"+badUrl ));
	}
	/**
	 * test an valid url with invalid JSON contents
	 * surprisingly, the Jackson implementation throws a RunTime
	 * (because it uses the Iterator interface)
	 * TODO: check this is acceptable behaviour, investigate alternate
	 * implementations
	 * @throws MalformedURLException
	 */
	@Test 
	public void testWrongUrl() throws MalformedURLException{
		String badUrlString ="http://www.google.com";
		URL badUrl=new URL(badUrlString);
		try{
			List<PersonAndLocation> people = peopleService.getFromUrl(badUrl);
			fail();
		}catch( RuntimeException e){
			log.info("exepcted failure");
		}
	}
	@Test
	public void testMissingData(){
		URL url = super.fileToURL("original-missing-cols.txt");
		List<PersonAndLocation> people = peopleService.getFromUrl(url);
		assertEquals(7,people.size() );
		/**
		 * check we got a warning
		 */
        Mockito.verify(mockAppender).doAppend(captorLoggingEvent.capture());
        final ch.qos.logback.classic.spi.LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        assertEquals( Level.WARN,loggingEvent.getLevel());
        assertThat(loggingEvent.getMessage(),CoreMatchers.containsString("failed at line 2"));
	}

}
