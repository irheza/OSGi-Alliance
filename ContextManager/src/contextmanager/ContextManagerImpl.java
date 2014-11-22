package contextmanager;

import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import preferencerepository.PreferenceRepositoryServices;
import entity.Map;
import entity.PlaceOfInterest;
import entity.RowColLocation;

public class ContextManagerImpl implements ContextManager {
	Map map;
	private int suhu;
	private String cuaca;
	private String time;
	private String currentLocation;
	private PreferenceRepositoryServices prefRepoServices;

	public ContextManagerImpl() {
		map = new Map("map.csv");
	}

	@Override
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location) {
		return map.getByLocation(location);
	}

	public void setSuhu(int suhu) {
		this.suhu = suhu;
	}

	public void setCuaca(String cuaca) {
		this.cuaca = cuaca;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String setCurrentLocation(String location) {
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
		return currentLocation;
	}

	public String getCompassDirective(RowColLocation from, PlaceOfInterest to) {
		if (to != null) {
			return map.getCompassDirective(from, to);
		}
		return null;
	}

	@Override
	public PlaceOfInterest getByName(String name) {
		return map.getByName(name);
	}

	public void setPreferencesRepositoryReference(BundleContext context, ServiceReference preferencesRepoReference) {
		prefRepoServices = (PreferenceRepositoryServices) context.getService(preferencesRepoReference);
	}
	
	@Override
	public void sendSuggestion() {
		System.out.println(prefRepoServices
				.getSuggestedServiceOfThisQuery("Bob", this.time, this.cuaca, this.suhu, this.currentLocation));
		
	}

}
