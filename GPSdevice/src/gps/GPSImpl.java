package gps;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import contextmanager.ContextManager;
import entity.PlaceOfInterest;
import entity.RowColLocation;
import map.*;

// TODO: Auto-generated Javadoc
/**
 * The Class GPSImpl.
 */
public class GPSImpl implements GPS {
	
	/** The current position. */
	Location currentPosition;
	String flag;
	/** The peta. */
	Graph peta;
    
    /** The loc def. */
    private Hashtable<String, RowColLocation> locDef;
    
    /**
     * Instantiates a new GPS impl.
     */
    public GPSImpl(){
    	locDef = new Hashtable<String, RowColLocation>(); 
        locDef.put("A", new RowColLocation(0,1));
        locDef.put("B", new RowColLocation(0,2));
        locDef.put("C", new RowColLocation(1,0));
        locDef.put("D", new RowColLocation(1,1));
        locDef.put("E", new RowColLocation(1,2));
        locDef.put("F", new RowColLocation(2,2));
        locDef.put("G", new RowColLocation(2,3));
        locDef.put("H", new RowColLocation(3,1));
        locDef.put("I", new RowColLocation(3,2));
    }
    
	/* (non-Javadoc)
	 * @see gps.GPS#getCurrentPosition()
	 */
	@Override
	public String getCurrentPosition() {
		// TODO Auto-generated method stub
		return currentPosition.getKordinat();
	}
	
	
	
	/* (non-Javadoc)
	 * @see gps.GPS#start()
	 */
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

	/* (non-Javadoc)
	 * @see gps.GPS#move()
	 */
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

	/* (non-Javadoc)
	 * @see gps.GPS#getObjectLokasi()
	 */
	@Override
	public Location getObjectLokasi() {
		// TODO Auto-generated method stub
		return currentPosition;
	}

	/* (non-Javadoc)
	 * @see gps.GPS#sendCurrentLocation(org.osgi.framework.BundleContext, org.osgi.framework.ServiceReference)
	 */
	public void sendCurrentLocation(BundleContext bundleContext,
			ServiceReference<?> contextmanagerServiceReference) {
		contextmanagerServiceReference = bundleContext
				.getServiceReference(ContextManager.class.getName());
		ContextManager contextManagerService = (ContextManager) bundleContext
				.getService(contextmanagerServiceReference);
		contextManagerService.setCurrentLocation(getCurrentPosition());
	}

	/* (non-Javadoc)
	 * @see gps.GPS#getCurrentLocationPOI(org.osgi.framework.BundleContext, org.osgi.framework.ServiceReference)
	 */
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

	/* (non-Javadoc)
	 * @see gps.GPS#getCompassDirective(org.osgi.framework.BundleContext, org.osgi.framework.ServiceReference, java.lang.String)
	 */
	@Override
	public String getCompassDirective(
			BundleContext bundleContext,
			ServiceReference<?> contextmanagerServiceReference, String to) {
		contextmanagerServiceReference = bundleContext
				.getServiceReference(ContextManager.class.getName());
		ContextManager contextManagerService = (ContextManager) bundleContext
				.getService(contextmanagerServiceReference);
		
		//System.out.println("CURRENT POSITION: "+locDef.get(currentPosition.getKordinat()));
		
		return contextManagerService.getCompassDirective(locDef.get(currentPosition.getKordinat()), contextManagerService.getByName(to));
	}

	@Override
	public String getFlag() {
		// TODO Auto-generated method stub
		return flag;
	}
	
	@Override
	public void setFlag(String flag) {
		// TODO Auto-generated method stub
		this.flag=flag;
	}
}
