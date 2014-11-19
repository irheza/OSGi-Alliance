package contextmanager;

import java.util.ArrayList;

import entity.Map;
import entity.PlaceOfInterest;

public class ContextManagerImpl implements ContextManager{
	Map map = new Map("map.csv");
	@Override
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceOfInterest getSinglePOI(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to) {
		// TODO Auto-generated method stub
		return null;
	}

}
