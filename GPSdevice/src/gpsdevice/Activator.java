package gpsdevice;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;



public class Activator implements BundleActivator {
	ServiceReference contextmanagerServiceReference;
	private static BundleContext context;
	GPSImpl gps;
	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Selamat datang di Location-Aware Tour Guide");
		System.out.println("");
		System.out.println("Silahkan masukkan pilihan anda:");
		System.out.println("1. Cari informasi mengenai sebuah tempat menarik");
		System.out.println("2. Cari tempat menarik di lokasi sekarang");
		System.out.println("3. Berikan petunjuk arah menuju sebuah tempat");
		System.out.println("E. Exit");
		gps = new GPSImpl();
		gps.start();
		Scanner input = new Scanner(System.in);
		if(input.next().equalsIgnoreCase("1"))
		{
			gps.move();
			gps.move();
			gps.move();
			gps.move();
			sentCurrentLocation(bundleContext);
			contextmanagerServiceReference= bundleContext.getServiceReference(ContextManager.class.getName());
		    ContextManager contextManagerService =(ContextManager)bundleContext.getService(contextmanagerServiceReference);
			System.out.println("Lokasi dari contextManager: "+contextManagerService.getCurrentLocationPosition());
		}
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	public void sentCurrentLocation(BundleContext bundleContext){
		contextmanagerServiceReference= bundleContext.getServiceReference(ContextManager.class.getName());
	    ContextManager contextManagerService =(ContextManager)bundleContext.getService(contextmanagerServiceReference);
	    contextManagerService.setCurrentLocation(gps.getCurrentPosition());
	}

}
