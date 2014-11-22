package gps;

import java.io.BufferedReader;
import java.io.IOException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;




import contextmanager.ContextManager;

// TODO: Auto-generated Javadoc
public class GPSFetcher implements Runnable {
	
	/** The gps. */
	GPS gps;
	boolean canMove=false;
	/** The bundle context. */
	BundleContext bundleContext;
	BufferedReader reader;
	
	/** The gps service reference. */
	ServiceReference gpsServiceReference;
	
	/**
	 * Instantiates a new GPS fetcher.
	 *
	 * @param bundleContext the bundle context
	 * @param gps the gps
	 */
	public GPSFetcher(BundleContext bundleContext,GPS gps, BufferedReader reader)
	{
		this.gps= gps;
		this.bundleContext = bundleContext;
		this.reader = reader;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true){
			gps.move();
			gps.sendCurrentLocation(bundleContext, gpsServiceReference);
			gpsServiceReference= bundleContext.getServiceReference(ContextManager.class.getName());
			ContextManager contextManagerService = (ContextManager) bundleContext.getService(gpsServiceReference);
			if(canMove){
				contextManagerService.sendSuggestion("GPS");
			}
			else
			{
				canMove=true;
			}
			
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
