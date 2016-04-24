package nearby;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.jtc.nearby.services.LocationService;
import ie.jtc.nearby.beans.LongitudeAndLatitude;

/**
 * test great circle distance calculation
 * against known results
 * @author John
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class GCDTest {

	@Autowired 
	LocationService locationservice;
	static final private String MYHOUSE="53.3655957,-6.240556";
	static final private String INTERCOM="53.3381985, -6.2592576";
	static final private String HOLYHEAD="53.279,-4.54";
	static final private String CORK="51.96,-8.52";
	static final private String ABERDEEN="57.169,-2.142";
	
	@Test
	public void testKnown() {
		double kms = locationservice.distanceInKilometres(new LongitudeAndLatitude(MYHOUSE),new LongitudeAndLatitude(INTERCOM));
		assertEquals(3.289604053358212,kms,0.1 );
		kms = locationservice.distanceInKilometres(new LongitudeAndLatitude(INTERCOM),new LongitudeAndLatitude(MYHOUSE));
		assertEquals(3.289604053358212,kms,0.1 );
		assertEquals(219.2,locationservice.distanceInKilometres(new LongitudeAndLatitude(MYHOUSE),new LongitudeAndLatitude(CORK)),0.1);
		assertEquals(496,locationservice.distanceInKilometres(new LongitudeAndLatitude(MYHOUSE),new LongitudeAndLatitude(ABERDEEN)),1);
		
	}

}
