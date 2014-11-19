package contextmanager;

import java.util.ArrayList;

import entity.PlaceOfInterest;

public interface ContextManager {
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location);
	public PlaceOfInterest getSinglePOI(String id);
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to);
}
