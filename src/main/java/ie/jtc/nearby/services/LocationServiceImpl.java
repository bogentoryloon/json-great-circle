package ie.jtc.nearby.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ie.jtc.nearby.beans.LongitudeAndLatitude;
@Service
public class LocationServiceImpl implements LocationService {

	private static Logger logger = LoggerFactory.getLogger(LocationService.class);
	private static double EARTH_R_KM = 6371.007;

	/**
	 * calculate the Great Circle Distance between 2 locations
	 * copied from
	 * http://introcs.cs.princeton.edu/java/12types/GreatCircle.java.html and
	 * http://edndoc.esri.com/arcobjects/9.0/Samples/Geometry/
	 * Great_Circle_Distance.htm
	 */
	@Override
	public double distanceInKilometres(LongitudeAndLatitude from, LongitudeAndLatitude to) {
		double x1 = Math.toRadians(from.getLongitude());
		double y1 = Math.toRadians(from.getLatitude());
		double x2 = Math.toRadians(to.getLongitude());
		double y2 = Math.toRadians(to.getLatitude());

		/*************************************************************************
		 * Compute using Haverside formula
		 *************************************************************************/
		double a = Math.pow(Math.sin((x2 - x1) / 2), 2)
				+ Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((y2 - y1) / 2), 2);

		// great circle distance in radians
		double angle2 = 2 * Math.asin(Math.min(1, Math.sqrt(a)));

		// convert back to degrees
		// angle2 = Math.toDegrees(angle2);

		// each degree on a great circle of Earth is 60 nautical miles
		double distance = EARTH_R_KM * angle2;

		logger.debug(from + " to " + to + "=" + distance);

		return distance;
	}

}
