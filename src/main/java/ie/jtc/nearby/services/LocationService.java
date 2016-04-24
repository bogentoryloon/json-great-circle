package ie.jtc.nearby.services;

import ie.jtc.nearby.beans.LongitudeAndLatitude;

public interface LocationService {
	abstract public  double distanceInKilometres(LongitudeAndLatitude from,
			LongitudeAndLatitude to);
}
