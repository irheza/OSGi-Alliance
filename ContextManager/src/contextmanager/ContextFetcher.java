package contextmanager;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sensor.Sensor;

public class ContextFetcher implements Runnable {
	BundleContext bundleContext;
	ContextManager contextManager;
	ServiceReference sensorServiceReference;
	Sensor sensorService;
	
	public ContextFetcher(BundleContext bundleContext, ContextManager contextManager){
		sensorServiceReference= bundleContext.getServiceReference(Sensor.class.getName());
	    sensorService =(Sensor)bundleContext.getService(sensorServiceReference);   
	    this.contextManager = contextManager;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			contextManager.setCuaca(sensorService.getCuaca());
	    	contextManager.setSuhu(sensorService.getSuhu());
	    	contextManager.setTime(sensorService.getTime());
	    	contextManager.sendSuggestion("Sensor");
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
