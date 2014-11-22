package gps;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import contextmanager.ContextManager;

// TODO: Auto-generated Javadoc
public class GPSFetcher implements Runnable {
	
	/** The gps. */
	GPS gps;
	
	/** The bundle context. */
	BundleContext bundleContext;
	
	/** The gps service reference. */
	ServiceReference gpsServiceReference;
	
	/**
	 * Instantiates a new GPS fetcher.
	 *
	 * @param bundleContext the bundle context
	 * @param gps the gps
	 */
	public GPSFetcher(BundleContext bundleContext,GPS gps)
	{
		this.gps= gps;
		this.bundleContext = bundleContext;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true){
			gps.move();
			System.out.println("It's moving, such wow, magic");
			gps.sendCurrentLocation(bundleContext, gpsServiceReference);
			gpsServiceReference= bundleContext.getServiceReference(ContextManager.class.getName());
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
