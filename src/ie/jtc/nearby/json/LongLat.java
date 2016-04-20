package ie.jtc.nearby.json;

public class LongLat {
	private double longitude;
	private double latitude;

	public LongLat(String longitude, String latitude) {
		setLongitude(longitude);
		setLatitude(latitude);
	}
	public LongLat() {		
	}
	public LongLat(String coords) {
		String pair[]=coords.split(",");
		setLongitude(pair[0]);
		setLatitude(pair[1]);

	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(String latitude2) {
		this.latitude=Double.parseDouble(latitude2);
		
	}
	public void setLongitude(String longitude2) {
		this.longitude=Double.parseDouble(longitude2);		
	}
	public String toString(){
		return Double.toString(longitude)+","+Double.toString(latitude);
	}
}
