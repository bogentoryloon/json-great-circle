package ie.jtc.nearby.beans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 *	representation of co-ordinates, specified in fractional degrees 
 * @author John
 *
 */
public class LongitudeAndLatitude {
	private double longitude;
	private double latitude;
	public LongitudeAndLatitude(String coords) {
		String pair[]=coords.split(",");
		setLongitudeString(pair[0]);
		setLatitudeString(pair[1]);

	}
	public void setLatitudeString(String latitude2) {
		this.latitude=Double.parseDouble(latitude2);
		
	}
	public void setLongitudeString(String longitude2) {
		this.longitude=Double.parseDouble(longitude2);		
	}
}
