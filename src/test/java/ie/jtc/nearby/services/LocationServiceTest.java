package ie.jtc.nearby.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.jtc.nearby.services.LocationService;
import ie.jtc.nearby.RunApp;
import ie.jtc.nearby.beans.LongitudeAndLatitude;

/**
 * test great circle distance calculation.
 * against known results (from google maps)
 * tests are run in both directions
 * @author John
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LocationServiceImpl.class)
public class LocationServiceTest {

	@Autowired 
	LocationService locationservice;
	
	static final LongitudeAndLatitude MYHOUSE = new LongitudeAndLatitude(53.3655957,-6.240556);	
	static final LongitudeAndLatitude INTERCOM=new LongitudeAndLatitude(53.3381985, -6.2592576);	
	static final LongitudeAndLatitude ABERDEEN=new LongitudeAndLatitude(57.169,-2.142);
	@Test
	public void testMyHouseToIntercom() {
		double kms = locationservice.distanceInKilometres(MYHOUSE,INTERCOM);
		assertEquals(3.289604053358212,kms,0.1 );
		kms = locationservice.distanceInKilometres(INTERCOM,MYHOUSE);
		assertEquals(3.289604053358212,kms,0.1 );
	}
	@Test
	public void testMyHouseToAberdeen(){		
		assertEquals(496,locationservice.distanceInKilometres(MYHOUSE,ABERDEEN),1);
		assertEquals(496,locationservice.distanceInKilometres(ABERDEEN,MYHOUSE),1);		
	}	

}
