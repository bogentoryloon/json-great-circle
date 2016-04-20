package ie.jtc.nearby.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PersonAndLocation  {

	private String user_id;
	private String name;
	@JsonIgnore
	private LongLat location=new LongLat();
	public String getLatitude() {
		return Double.toString(location.getLatitude());
	}
	public void setLatitude(String latitude) {
		location.setLatitude(latitude);
	}
	public String getLongitude() {
		return Double.toString(location.getLongitude());
	}
	public void setLongitude(String longitude) {
		location.setLongitude(longitude);
	}
	public String getUser_id() {
		// don't return null because it's used in comparator
		// yes it's a hack but I'm out of time
		return user_id==null?"":user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public LongLat getLocation() {
		return location;
	}
	

	public String toString(){
		return getName()+"("+getUser_id()+")";
	}
}
