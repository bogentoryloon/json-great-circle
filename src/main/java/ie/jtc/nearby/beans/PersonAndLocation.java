package ie.jtc.nearby.beans;

import lombok.Data;
/**
 * our core data item, the demographic and locational data
 * derived from our JSON input
 * @author John
 *
 */
@Data
public class PersonAndLocation {
	private String name;
	private String userId;
	private LongitudeAndLatitude location;
}
