package contextmanager;

import java.util.ArrayList;

import entity.Map;
import entity.PlaceOfInterest;

public class ContextManagerImpl implements ContextManager{
	Map map;
	private int suhu;
	private String cuaca;
	private String time;
	
	public ContextManagerImpl() {
		map = new Map("map.csv");
	}
	private String currentLocation;
	
	@Override
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location) {
		return map.getByLocation(location);
	}
	
	public void setSuhu(int suhu)
	{
		this.suhu = suhu;
	}
	
	public void setCuaca(String cuaca)
	{
		this.cuaca=cuaca;
	}
	
	public void setTime(String time)
	{
		this.time=time;
	}
	
	public String setCurrentLocation(String location)
	{
		this.currentLocation = location;
		return location;
	}
	

	@Override
	public PlaceOfInterest getSinglePOI(String id) {
		return map.getByID(id);
	}

	@Override
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to) {
		return map.getCompassDirective(from, to);
	}

	@Override
	public String getCurrentLocationPosition() {
		// TODO Auto-generated method stub
		return currentLocation;
	}

}
