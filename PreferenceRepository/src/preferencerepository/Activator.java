package preferencerepository;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.service.prefs.PreferencesService;

public class Activator implements BundleActivator {
	/**
	 * Create global variables for this bundle
	 */
	ServiceRegistration preferencesRepoRegistration;
	private ServiceTracker tracker;
	PreferenceRepositoryServicesImpl services = new PreferenceRepositoryServicesImpl();
	
	/**
	 * Called when the bundle start
	 * Will open tracker and init preferences from file, then register service for other bundles
	 */
	public void start(BundleContext context) throws Exception {
		tracker = new ServiceTracker(context, PreferencesService.class.getName(), null);
		tracker.open();
		services.initPreferences(tracker);
		preferencesRepoRegistration = context.registerService(PreferenceRepositoryServices.class.getName(), services, null);
	}
	
	/**
	 * Called when the bundle stop
	 * Will close tracker and clean preferences, also unregister services this bundle provided
	 */
	public void stop(BundleContext context) throws Exception {
		// clean up
		tracker.close();
		tracker = null;
		services.cleanPreferencesService();
		preferencesRepoRegistration.unregister();
	}

}
