package contextmanager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import preferencerepository.PreferenceRepositoryServices;

// TODO: Auto-generated Javadoc
/**
 * The Class Activator.
 */
public class Activator implements BundleActivator {
	
	/** The sensor service reference. */
	ServiceReference sensorServiceReference;
	
	/** The preferences repo reference. */
	ServiceReference preferencesRepoReference;
	
	/** The server. */
	ContextManager server;
	
	/** The context fetcher. */
	Thread contextFetcher;
	
	/** The context service registration. */
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
		
		contextServiceRegistration = context.registerService(
				ContextManager.class.getName(), server, null);

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
