package contextmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import entity.PlaceOfInterest;
import entity.RowColLocation;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContextManager.
 */
public interface ContextManager {	
	
	/**
	 * Gets the current location info.
	 *
	 * @param location the location
	 * @return the current location info
	 */
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location);
	
	/**
	 * Gets the single poi.
	 *
	 * @param id the id
	 * @return the single poi
	 */
	public PlaceOfInterest getSinglePOI(String id);
	
	/**
	 * Gets the compass directive.
	 *
	 * @param from the from
	 * @param to the to
	 * @return the compass directive
	 */
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to);
	
	/**
	 * Gets the compass directive.
	 *
	 * @param from the from
	 * @param to the to
	 * @return the compass directive
	 */
	public String getCompassDirective(RowColLocation from, PlaceOfInterest to);
	
	/**
	 * Sets the cuaca.
	 *
	 * @param cuaca the new cuaca
	 */
	public void setCuaca(String cuaca);
	
	/**
	 * Sets the suhu.
	 *
	 * @param suhu the new suhu
	 */
	public void setSuhu(int suhu);
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time);
	
	/**
	 * Sets the current location.
	 *
	 * @param location the location
	 * @return the string
	 */
	public String setCurrentLocation(String location);
	
	/**
	 * Gets the current location position.
	 *
	 * @return the current location position
	 */
	public String getCurrentLocationPosition();
	
	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	public PlaceOfInterest getByName(String name);
	
	/**
	 * Send suggestion.
	 * @throws IOException 
	 */
	
	public String getFlag();
	public void setFlag(String flag);
	public void sendSuggestion(String whichContext, BufferedReader reader) throws IOException;
	public String[] getSuggestedPlace();
	/**
	 * Sets the preferences repository reference.
	 *
	 * @param context the context
	 * @param preferencesRepoReference the preferences repo reference
	 */
	public void setPreferencesRepositoryReference(BundleContext context, ServiceReference preferencesRepoReference);
}
