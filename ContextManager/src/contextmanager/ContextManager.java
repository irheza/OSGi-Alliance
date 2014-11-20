package contextmanager;

import java.util.ArrayList;

import entity.PlaceOfInterest;

public interface ContextManager {	
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location);
	public PlaceOfInterest getSinglePOI(String id);
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to);
<<<<<<< HEAD
	public void setCuaca(String cuaca);
	public void setSuhu(int suhu);
	public void setTime(String time);
=======
	public String setCurrentLocation(String location);
	public String getCurrentLocationPosition();
>>>>>>> branch 'master' of https://github.com/irheza/OSGi-Alliance.git
}
