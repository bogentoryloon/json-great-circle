package ie.jtc.nearby.jackson;

import lombok.Data;

@Data
public class PersonAndLocationDTO {
	private double longitude;
	private double latitude;
	private String user_id;
	private String name;
}
