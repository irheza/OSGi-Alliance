package gps;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;

public class GPSFetcher implements Runnable {
	GPS gps;
	BundleContext bundleContext;
	ServiceReference gpsServiceReference;
	public GPSFetcher(BundleContext bundleContext,GPS gps)
	{
		this.gps= gps;
		this.bundleContext = bundleContext;
	}

	@Override
	public void run() {
		while(true){
			gps.move();
			System.out.println("It's moving, such wow, magic");
			gps.sendCurrentLocation(bundleContext, gpsServiceReference);
			ContextManager contextManagerService = (ContextManager) bundleContext.getService(gpsServiceReference);
			contextManagerService.sendSuggestion();
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
