package nearby;

import static org.junit.Assert.*;

import org.junit.Test;

import ie.jtc.nearby.json.LocationService;
import ie.jtc.nearby.json.LongLat;

/**
 * test great circle distance calculation
 * against known results
 * @author John
 *
 */
public class GCDTest {

	static final private String MYHOUSE="53.3655957,-6.240556";
	static final private String INTERCOM="53.3381985, -6.2592576";
	static final private String HOLYHEAD="53.279,-4.54";
	static final private String CORK="51.96,-8.52";
	static final private String ABERDEEN="57.169,-2.142";
	
	@Test
	public void testKnown() {
		double kms = LocationService.distTanceInKilometres(new LongLat(MYHOUSE),new LongLat(INTERCOM));
		assertEquals(3.289604053358212,kms,0.1 );
		kms = LocationService.distTanceInKilometres(new LongLat(INTERCOM),new LongLat(MYHOUSE));
		assertEquals(3.289604053358212,kms,0.1 );
		assertEquals(219.2,LocationService.distTanceInKilometres(new LongLat(MYHOUSE),new LongLat(CORK)),0.1);
		assertEquals(496,LocationService.distTanceInKilometres(new LongLat(MYHOUSE),new LongLat(ABERDEEN)),1);
		
	}

}
