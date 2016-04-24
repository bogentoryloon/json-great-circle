package ie.jtc.nearby.beans;

import lombok.Data;

@Data
public class PersonAndLocation {
	private String name;
	private String userId;
	private LongitudeAndLatitude location;
}
