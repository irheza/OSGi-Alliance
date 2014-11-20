package contextmanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import sensor.Sensor;

public class Activator implements BundleActivator {
	 ServiceReference sensorServiceReference;
	 ContextManager server;
	 Thread contextFetcher;
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World!!");
		server = new ContextManagerImpl();
		
		contextFetcher = new Thread(new ContextFetcher(context, server));
		contextFetcher.start();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
	}

}
