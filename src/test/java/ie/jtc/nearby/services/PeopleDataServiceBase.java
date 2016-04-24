package ie.jtc.nearby.services;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.LoggingEventVO;
import ch.qos.logback.core.Appender;

/**
 * some common functionality for the trickier tests
 * e.g. we need to test logging
 * @author John
 *
 */
public class PeopleDataServiceBase {

	@Autowired
	PeopleDataService peopleService;

	/**
	 * the following code for verifying log messages was taken from
	 * http://bloodredsun.com/2014/06/03/checking-logback-based-logging-in-unit-tests/
	 */
    @Mock
    protected Appender mockAppender;
    @Captor
	protected ArgumentCaptor<LoggingEvent> captorLoggingEvent = ArgumentCaptor.forClass(LoggingEvent.class);    
    @Before
    public void setup() {
    	/**
    	 * we can only test the implementation (LogBack),
    	 * despite the application implementing the facade(SLF4J)
    	 */
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);	    
	    mockAppender = Mockito.mock(Appender.class);
	    Mockito.when(mockAppender.getName()).thenReturn("MOCK");
	    root.addAppender(mockAppender);
    }
    @After
    public void teardown() {
    	ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.detachAppender(mockAppender);
    }    

	/**
	 * return a local file as a URL 
	 * @return
	 */
	protected URL fileToURL(String fileName) {
		URL url = PeopleDataServiceUrlReading.class.getResource("/"+fileName);
		return url;
	}

}