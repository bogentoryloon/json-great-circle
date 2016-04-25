package ie.jtc.nearby.services;

import org.springframework.stereotype.Service;
import ie.jtc.nearby.beans.LongitudeAndLatitude;

/**
 * calculator for Great Circle Distance
 * @author John
 *
 */
@Service
public interface LocationService {
	abstract public  double distanceInKilometres(LongitudeAndLatitude from,
			LongitudeAndLatitude to);
}
