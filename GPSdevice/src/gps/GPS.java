package gps;

import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import entity.PlaceOfInterest;
import map.Location;

public interface GPS {
	public String getCurrentPosition();
	public void start();
	public String move();
	public Location getObjectLokasi();
	public void sendCurrentLocation(BundleContext bundleContext, ServiceReference<?> contextmanagerServiceReference);
	public ArrayList<PlaceOfInterest> getCurrentLocationPOI(BundleContext bundleContext, ServiceReference<?> contextmanagerServiceReference);
	public String getCompassDirective(BundleContext bundleContext, ServiceReference<?> contextmanagerServiceReference, String to);
}
