package contextmanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import sensor.Sensor;

public class Activator implements BundleActivator {
	 ServiceReference sensorServiceReference;
<<<<<<< HEAD
	 ContextManager server;
	 Thread contextFetcher;
=======
	 ContextManagerImpl server;
	 ServiceRegistration contextServiceRegistration;
>>>>>>> branch 'master' of https://github.com/irheza/OSGi-Alliance.git
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World!!");
		server = new ContextManagerImpl();
<<<<<<< HEAD
		
		contextFetcher = new Thread(new ContextFetcher(context, server));
		contextFetcher.start();
=======
		ContextManager contextService = new ContextManagerImpl();
	    contextServiceRegistration =context.registerService(ContextManager.class.getName(), contextService, null);
>>>>>>> branch 'master' of https://github.com/irheza/OSGi-Alliance.git
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
<<<<<<< HEAD
=======
		 contextServiceRegistration.unregister();
	}
	
	public void getDataSensor(BundleContext context)
	{
		
		sensorServiceReference= context.getServiceReference(Sensor.class.getName());
	    Sensor sensorService =(Sensor)context.getService(sensorServiceReference);
	    server.setCuaca(sensorService.getCuaca());
	    server.setSuhu(sensorService.getSuhu());
	    server.setTime(sensorService.getTime());
>>>>>>> branch 'master' of https://github.com/irheza/OSGi-Alliance.git
	}

}
