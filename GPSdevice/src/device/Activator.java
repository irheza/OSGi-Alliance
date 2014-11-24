package device;

import entity.PlaceOfInterest;
import gps.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;

// TODO: Auto-generated Javadoc
public class Activator implements BundleActivator {
	

	/** The reader. */
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	/** The gps fetcher. */
	Thread gpsFetcher;
	
	/** The contextmanager service reference. */
	ServiceReference contextmanagerServiceReference;
	
	/** The context. */
	private static BundleContext context;
	
	/** The gps. */
	GPS gps;

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	static BundleContext getContext() {
		return context;
	}

	/*
	 * Fungsi yang akan menjalankan menu utama
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		contextmanagerServiceReference = bundleContext.getServiceReference(ContextManager.class.getName());
		ContextManager contextManagerService = (ContextManager) bundleContext.getService(contextmanagerServiceReference);
		gps = new GPSImpl();
		gps.start();
		gpsFetcher = new Thread(new GPSFetcher(bundleContext, gps,reader));
		gpsFetcher.start();

		
		MainMenu menu = new MainMenu();
		menu.toMainMenu(contextManagerService, bundleContext, reader, gps, contextmanagerServiceReference,"");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
