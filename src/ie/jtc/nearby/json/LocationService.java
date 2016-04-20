package ie.jtc.nearby.json;

import org.apache.log4j.Logger;



public class LocationService {

	private static Logger logger = Logger.getLogger(LocationService.class);
	private static double EARTH_R_KM = 6371.007;

	/**
	 * copied from
	 * http://introcs.cs.princeton.edu/java/12types/GreatCircle.java.html and
	 * http://edndoc.esri.com/arcobjects/9.0/Samples/Geometry/
	 * Great_Circle_Distance.htm
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static double distTanceInKilometres(LongLat from, LongLat to) {
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
