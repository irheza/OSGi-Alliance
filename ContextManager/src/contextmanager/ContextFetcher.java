package contextmanager;

import java.io.IOException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sensor.Sensor;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextFetcher.
 */
public class ContextFetcher implements Runnable {
	boolean canMove=false;
	/** The bundle context. */
	BundleContext bundleContext;
	
	/** The context manager. */
	ContextManager contextManager;
	
	/** The sensor service reference. */
	ServiceReference sensorServiceReference;
	
	/** The sensor service. */
	Sensor sensorService;
	
	/**
	 * Instantiates a new context fetcher.
	 *
	 * @param bundleContext the bundle context
	 * @param contextManager the context manager
	 */
	public ContextFetcher(BundleContext bundleContext, ContextManager contextManager){
		sensorServiceReference= bundleContext.getServiceReference(Sensor.class.getName());
	    sensorService =(Sensor)bundleContext.getService(sensorServiceReference);   
	    this.contextManager = contextManager;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		while(true){
			contextManager.setCuaca(sensorService.getCuaca());
	    	contextManager.setSuhu(sensorService.getSuhu());
	    	contextManager.setTime(sensorService.getTime());
//	    	if(canMove)
//			{
//				//contextManager.sendSuggestion("Sensor");
//			}
//			else
//			{
//				canMove=true;
//			}
	    	
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
