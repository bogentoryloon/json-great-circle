package ie.jtc.nearby.jackson;

import lombok.Data;

/**
 * Data Transfer Object
 * While this is controversial pattern,
 * in this case we have conflicting requirements
 * from our application and com.fasterxml.jackson.databind,
 * which in turn maps direct to JSON.
 * On this occasion a clean seperation of the two is considerred 
 * worthwhile
 * @author John
 *
 */
@Data
public class PersonAndLocationDTO {
	private double longitude;
	private double latitude;
	private String user_id;
	private String name;
}
