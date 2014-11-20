package contextmanager;

import java.util.ArrayList;

import entity.Map;
import entity.PlaceOfInterest;

public class ContextManagerImpl implements ContextManager{
	Map map = new Map("map.csv");
	private int suhu;
	private String cuaca;
	private String time;
	@Override
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location) {
		// TODO Auto-generated method stub
		return null;
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
