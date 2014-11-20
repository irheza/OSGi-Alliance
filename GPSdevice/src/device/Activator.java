package device;

import entity.PlaceOfInterest;
import gps.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;

public class Activator implements BundleActivator {
	final int INFO_TEMPAT_MENARIK = 1;
	final int TEMPAT_MENARIK_LOKASI_SKRG = 2;
	final int PETUNJUK_ARAH = 3;
	
	ServiceReference contextmanagerServiceReference;
	private static BundleContext context;
	GPS gps;
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
		int mode = Integer.parseInt(input.next().trim());
		
		if(mode == INFO_TEMPAT_MENARIK)
		{
			gps.move();
			gps.move();
			gps.move();
			gps.move();
			gps.sendCurrentLocation(bundleContext, contextmanagerServiceReference);
			contextmanagerServiceReference= bundleContext.getServiceReference(ContextManager.class.getName());
		    ContextManager contextManagerService =(ContextManager)bundleContext.getService(contextmanagerServiceReference);
			System.out.println("Lokasi dari contextManager: "+contextManagerService.getCurrentLocationPosition());
		}else if(mode == TEMPAT_MENARIK_LOKASI_SKRG){
			ArrayList<PlaceOfInterest> pois = gps.getCurrentLocationPOI(bundleContext, contextmanagerServiceReference);
			for(PlaceOfInterest p:pois){
				System.out.printf("NAMA: %s\n", p.getName());
			}
		}else if(mode == PETUNJUK_ARAH){
			
		}
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	

}
