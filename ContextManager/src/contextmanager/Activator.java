package contextmanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import preferencerepository.PreferenceRepositoryServices;
import sensor.Sensor;

public class Activator implements BundleActivator {
	ServiceReference sensorServiceReference;
	ServiceReference preferencesRepoReference;
	ContextManager server;
	Thread contextFetcher;
	ServiceRegistration contextServiceRegistration;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		server = new ContextManagerImpl();
		
		preferencesRepoReference = context.getServiceReference(PreferenceRepositoryServices.class.getName());
		server.setPreferencesRepositoryReference(context, preferencesRepoReference);

		contextFetcher = new Thread(new ContextFetcher(context, server));
		contextFetcher.start();
		
		ContextManager contextService = new ContextManagerImpl();
		contextServiceRegistration = context.registerService(
				ContextManager.class.getName(), contextService, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		contextServiceRegistration.unregister();
	}
}
