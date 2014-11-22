package contextmanager;

import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import preferencerepository.PreferenceRepositoryServices;
import entity.Map;
import entity.PlaceOfInterest;
import entity.RowColLocation;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextManagerImpl.
 */
public class ContextManagerImpl implements ContextManager {
	
	/** The map. */
	Map map;
	
	/** The suhu. */
	private int suhu = Integer.MAX_VALUE;
	
	/** The cuaca. */
	private String cuaca = "";
	
	/** The time. */
	private String time = "";
	
	/** The current location. */
	private String currentLocation = "";
	
	/** The pref repo services. */
	private PreferenceRepositoryServices prefRepoServices;

	/**
	 * Instantiates a new context manager impl.
	 */
	public ContextManagerImpl() {
		map = new Map("map.csv");
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCurrentLocationInfo(java.lang.String)
	 */
	@Override
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location) {
		return map.getByLocation(location);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setSuhu(int)
	 */
	public void setSuhu(int suhu) {
		this.suhu = suhu;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setCuaca(java.lang.String)
	 */
	public void setCuaca(String cuaca) {
		this.cuaca = cuaca;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setTime(java.lang.String)
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setCurrentLocation(java.lang.String)
	 */
	public String setCurrentLocation(String location) {
		this.currentLocation = location;
		return location;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getSinglePOI(java.lang.String)
	 */
	@Override
	public PlaceOfInterest getSinglePOI(String id) {
		return map.getByID(id);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCompassDirective(entity.PlaceOfInterest, entity.PlaceOfInterest)
	 */
	@Override
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to) {
		return map.getCompassDirective(from, to);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCurrentLocationPosition()
	 */
	@Override
	public String getCurrentLocationPosition() {
		return currentLocation;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCompassDirective(entity.RowColLocation, entity.PlaceOfInterest)
	 */
	public String getCompassDirective(RowColLocation from, PlaceOfInterest to) {
		if (to != null) {
			return map.getCompassDirective(from, to);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getByName(java.lang.String)
	 */
	@Override
	public PlaceOfInterest getByName(String name) {
		return map.getByName(name);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setPreferencesRepositoryReference(org.osgi.framework.BundleContext, org.osgi.framework.ServiceReference)
	 */
	public void setPreferencesRepositoryReference(BundleContext context, ServiceReference preferencesRepoReference) {
		prefRepoServices = (PreferenceRepositoryServices) context.getService(preferencesRepoReference);
	}
	
	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#sendSuggestion()
	 */
	@Override
	public void sendSuggestion(String whichContext) {
		System.out.println(prefRepoServices
				.getSuggestedServiceOfThisQuery("Bob", this.time, this.cuaca, this.suhu, this.currentLocation));
	}
}
