package gps;

import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import entity.PlaceOfInterest;
import map.Location;

// TODO: Auto-generated Javadoc
public interface GPS {
	
	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public String getCurrentPosition();
	
	/**
	 * Start.
	 */
	public void start();
	
	/**
	 * Move.
	 *
	 * @return the string
	 */
	public String move();
	public String getFlag();
	public void setFlag(String flag);
	
	/**
	 * Gets the object lokasi.
	 *
	 * @return the object lokasi
	 */
	public Location getObjectLokasi();
	
	/**
	 * Send current location.
	 *
	 * @param bundleContext the bundle context
	 * @param contextmanagerServiceReference the contextmanager service reference
	 */
	public void sendCurrentLocation(BundleContext bundleContext, ServiceReference<?> contextmanagerServiceReference);
	
	/**
	 * Gets the current location poi.
	 *
	 * @param bundleContext the bundle context
	 * @param contextmanagerServiceReference the contextmanager service reference
	 * @return the current location poi
	 */
	public ArrayList<PlaceOfInterest> getCurrentLocationPOI(BundleContext bundleContext, ServiceReference<?> contextmanagerServiceReference);
	
	/**
	 * Gets the compass directive.
	 *
	 * @param bundleContext the bundle context
	 * @param contextmanagerServiceReference the contextmanager service reference
	 * @param to the to
	 * @return the compass directive
	 */
	public String getCompassDirective(BundleContext bundleContext, ServiceReference<?> contextmanagerServiceReference, String to);
}
