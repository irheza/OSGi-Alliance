package device;

import entity.PlaceOfInterest;
import gps.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;

// TODO: Auto-generated Javadoc
public class Activator implements BundleActivator {
	
	/** The constant for tempat menarik. */
	final int INFO_TEMPAT_MENARIK = 1;
	
	/** The constant for tempat menarik lokasi skrg. */
	final int TEMPAT_MENARIK_LOKASI_SKRG = 2;
	
	/** The constant for petunjuk arah. */
	final int PETUNJUK_ARAH = 3;

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
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		contextmanagerServiceReference = bundleContext.getServiceReference(ContextManager.class.getName());
		ContextManager contextManagerService = (ContextManager) bundleContext.getService(contextmanagerServiceReference);
		System.out.println("CMService GPS :" + contextManagerService.toString());
		System.out.println("Selamat datang di Location-Aware Tour Guide");
		System.out.println("");
		System.out.println("Silahkan masukkan pilihan anda:");
		System.out.println("1. Cari informasi mengenai sebuah tempat menarik");
		System.out.println("2. Cari tempat menarik di lokasi sekarang");
		System.out.println("3. Berikan petunjuk arah menuju sebuah tempat");
		System.out.println("E. Exit");
		gps = new GPSImpl();
		gps.start();
		gpsFetcher = new Thread(new GPSFetcher(bundleContext, gps));
		gpsFetcher.start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		int mode = Integer.parseInt(reader.readLine().trim());

		if (mode == INFO_TEMPAT_MENARIK) {
			//System.out.println("Lokasi dari contextManager: "
			//		+ contextManagerService.getCurrentLocationPosition());
			//anggep nama tempat tidak mempunyai spasi 
			System.out.print("Masukkan nama tempat yang ingin dicari informasinya: ");
			String namatempat = reader.readLine();
			System.out.println(contextManagerService.getByName(namatempat));
		} else if (mode == TEMPAT_MENARIK_LOKASI_SKRG) {
			ArrayList<PlaceOfInterest> pois = gps.getCurrentLocationPOI(
					bundleContext, contextmanagerServiceReference);

			for (int i = 0; i < pois.size(); i++) {
				System.out
						.printf("%d NAMA: %s\n", i + 1, pois.get(i).getName());


				System.out.println("pilih poi: ");
				int poiSelected = Integer.parseInt(reader.readLine().trim()) - 1;

				PlaceOfInterest poi = pois.get(poiSelected);

				System.out.println(poi.toString());
			}
		} else if (mode == PETUNJUK_ARAH) {
			System.out.println("sebut nama tempat yang mau dicari:");

			String toStr = reader.readLine();
			String cd = gps.getCompassDirective(bundleContext,
					contextmanagerServiceReference, toStr);
			if (cd != null) {
				//jika tempat yang dicari berada di posisi anda saat ini
				if(cd.equals("POSISI SAMA"))
				{
					System.out.println(toStr+" berada di lokasi anda sekarang.");
				}
				else
				{
					System.out.println(toStr+" berada di "+cd+ " dari lokasi anda sekarang.");
				}
			} else {
				System.out.println("lokasi tidak ditemukan");
			}
		}

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
