package contextmanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import sensor.Sensor;

public class Activator implements BundleActivator {
	 ServiceReference sensorServiceReference;
	 ContextManagerImpl server;
	 ServiceRegistration contextServiceRegistration;
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World!!");
		server = new ContextManagerImpl();
		ContextManager contextService = new ContextManagerImpl();
	    contextServiceRegistration =context.registerService(ContextManager.class.getName(), contextService, null);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
		 contextServiceRegistration.unregister();
	}
	
	public void getDataSensor(BundleContext context)
	{
		
		sensorServiceReference= context.getServiceReference(Sensor.class.getName());
	    Sensor sensorService =(Sensor)context.getService(sensorServiceReference);
	    server.setCuaca(sensorService.getCuaca());
	    server.setSuhu(sensorService.getSuhu());
	    server.setTime(sensorService.getTime());
	}

}
