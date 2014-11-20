package gps;

import java.util.ArrayList;
import java.util.Random;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;
import entity.PlaceOfInterest;
import map.*;

public class GPSImpl implements GPS {
	Location currentPosition;
	Graph peta;

	@Override
	public String getCurrentPosition() {
		// TODO Auto-generated method stub
		return currentPosition.getKordinat();
	}

	@Override
	public void start() {
		ArrayList<Location> listLokasi = new ArrayList<Location>();
		Location a = new Location("A");
		Location b = new Location("B");
		Location c = new Location("C");
		Location d = new Location("D");
		Location e = new Location("E");
		Location f = new Location("F");
		Location g = new Location("G");
		Location h = new Location("H");
		Location i = new Location("I");
		listLokasi.add(a);
		listLokasi.add(b);
		listLokasi.add(c);
		listLokasi.add(d);
		listLokasi.add(e);
		listLokasi.add(f);
		listLokasi.add(g);
		listLokasi.add(h);
		listLokasi.add(i);
		peta = new Graph(listLokasi);
		peta.addEdge(a, b);
		peta.addEdge(a, d);
		peta.addEdge(b, e);
		peta.addEdge(c, d);
		peta.addEdge(d, e);
		peta.addEdge(e, f);
		peta.addEdge(f, g);
		peta.addEdge(f, i);
		peta.addEdge(h, i);
		currentPosition = a;

	}

	@Override
	public String move() {
		Random rand = new Random();
		boolean val = rand.nextInt(2) == 0;
		// kemungkinan 50% diam ditempat, selain itu bergerak ketempat lain
		if (val == true) {
			ArrayList<Location> tetangga = peta.getTetangga(currentPosition);
			currentPosition = tetangga.get(rand.nextInt(tetangga.size()));
		}
		return currentPosition.getKordinat();
	}

	@Override
	public Location getObjectLokasi() {
		// TODO Auto-generated method stub
		return currentPosition;
	}

	public void sendCurrentLocation(BundleContext bundleContext,
			ServiceReference<?> contextmanagerServiceReference) {
		contextmanagerServiceReference = bundleContext
				.getServiceReference(ContextManager.class.getName());
		ContextManager contextManagerService = (ContextManager) bundleContext
				.getService(contextmanagerServiceReference);
		contextManagerService.setCurrentLocation(getCurrentPosition());
	}

	@Override
	public ArrayList<PlaceOfInterest> getCurrentLocationPOI(
			BundleContext bundleContext,
			ServiceReference<?> contextmanagerServiceReference) {
		contextmanagerServiceReference = bundleContext
				.getServiceReference(ContextManager.class.getName());
		ContextManager contextManagerService = (ContextManager) bundleContext
				.getService(contextmanagerServiceReference);
		return contextManagerService
				.getCurrentLocationInfo(getCurrentPosition());
	}

}
