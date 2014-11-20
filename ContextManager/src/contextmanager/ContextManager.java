package contextmanager;

import java.util.ArrayList;

import entity.PlaceOfInterest;
import entity.RowColLocation;

public interface ContextManager {	
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location);
	public PlaceOfInterest getSinglePOI(String id);
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to);
	public String getCompassDirective(RowColLocation from, PlaceOfInterest to);
	public void setCuaca(String cuaca);
	public void setSuhu(int suhu);
	public void setTime(String time);
	public String setCurrentLocation(String location);
	public String getCurrentLocationPosition();
	public PlaceOfInterest getByName(String name);
}
