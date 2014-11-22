package contextmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import preferencerepository.PreferenceRepositoryServices;
import entity.Map;
import entity.PlaceOfInterest;
import entity.RowColLocation;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextManagerImpl.
 */
public class ContextManagerImpl implements ContextManager {
	
	/** The map. */
	Map map;
	
	/** The suhu. */
	private int suhu = Integer.MAX_VALUE;
	
	/** The cuaca. */
	private String cuaca = "";
	
	/** The time. */
	private String time = "";
	
	/** The current location. */
	private String currentLocation = "";
	
	/** The pref repo services. */
	private PreferenceRepositoryServices prefRepoServices;

	/**
	 * Instantiates a new context manager impl.
	 */
	public ContextManagerImpl() {
		map = new Map("map.csv");
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCurrentLocationInfo(java.lang.String)
	 */
	@Override
	public ArrayList<PlaceOfInterest> getCurrentLocationInfo(String location) {
		return map.getByLocation(location);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setSuhu(int)
	 */
	public void setSuhu(int suhu) {
		this.suhu = suhu;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setCuaca(java.lang.String)
	 */
	public void setCuaca(String cuaca) {
		this.cuaca = cuaca;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setTime(java.lang.String)
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setCurrentLocation(java.lang.String)
	 */
	public String setCurrentLocation(String location) {
		this.currentLocation = location;
		return location;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getSinglePOI(java.lang.String)
	 */
	@Override
	public PlaceOfInterest getSinglePOI(String id) {
		return map.getByID(id);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCompassDirective(entity.PlaceOfInterest, entity.PlaceOfInterest)
	 */
	@Override
	public String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to) {
		return map.getCompassDirective(from, to);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCurrentLocationPosition()
	 */
	@Override
	public String getCurrentLocationPosition() {
		return currentLocation;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getCompassDirective(entity.RowColLocation, entity.PlaceOfInterest)
	 */
	public String getCompassDirective(RowColLocation from, PlaceOfInterest to) {
		if (to != null) {
			return map.getCompassDirective(from, to);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#getByName(java.lang.String)
	 */
	@Override
	public PlaceOfInterest getByName(String name) {
		return map.getByName(name);
	}

	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#setPreferencesRepositoryReference(org.osgi.framework.BundleContext, org.osgi.framework.ServiceReference)
	 */
	public void setPreferencesRepositoryReference(BundleContext context, ServiceReference preferencesRepoReference) {
		prefRepoServices = (PreferenceRepositoryServices) context.getService(preferencesRepoReference);
	}
	
	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#sendSuggestion()
	 */
	@Override
	public void sendSuggestion(String whichContext) throws IOException {
		if(whichContext.equals("GPS")){
			System.out.println("Selamat datang di "+ this.currentLocation);
			ArrayList<String> serviceSuggested = prefRepoServices
				.getSuggestedServiceOfThisQuery("Bob", this.time, this.cuaca, this.suhu, this.currentLocation);
			if(!serviceSuggested.isEmpty()){
				checkSuggestionPlace();
				
			}
		}
		
	}
	
	private void checkSuggestionPlace() throws IOException{
		ArrayList<PlaceOfInterest> interestingPlace = getCurrentLocationInfo(this.currentLocation);
		PlaceOfInterest[] poi = new PlaceOfInterest[interestingPlace.size()];
		poi = interestingPlace.toArray(poi);
		
		ArrayList<String> placeName = new ArrayList<String>();
		
		for(PlaceOfInterest place : poi){
			if(checkServicePlace(place)){
				placeName.add(place.getName());
			}
		}
		printSuggestionMessage(placeName);	
	}
	
	private boolean checkServicePlace(PlaceOfInterest place){
		ArrayList<String> serviceOfferList =  place.getService();
		String[] serviceList =  new String[serviceOfferList.size()];
		serviceList = serviceOfferList.toArray(serviceList);
		
		ArrayList<String> serviceSuggested = prefRepoServices
				.getSuggestedServiceOfThisQuery("Bob", this.time, this.cuaca, this.suhu, this.currentLocation);
		String[] serviceWanted =  new String[serviceSuggested.size()];
		serviceWanted = serviceSuggested.toArray(serviceList);
		
		ArrayList<String> compareList = new ArrayList<String>();
		if(!serviceOfferList.isEmpty()){
			for(String s : serviceList){
				for(String x: serviceWanted){
					if(s.equals(x)){
						compareList.add(s);
						System.out.println(s);
						if(!compareList.isEmpty())  return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private void printSuggestionMessage(ArrayList<String> suggestionList) throws IOException{
		String[] suggested =  new String[suggestionList.size()];
		suggested = suggestionList.toArray(suggested);
		System.out.println("Berdasarkan preferensi anda, tempat-tempat berikut menarik untuk dikunjungi. Silahkan pilih salah satu untuk mendapatkan informasi lebih lanjut:");
		int choice = 1;
		for(String s : suggested){
			System.out.println(choice+". "+s);
		}
		System.out.println("B. Back");
		
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
		String optionChoose = reader.readLine();
		
	}
	
	private void getPlaceInformation(String infoIndex){
		
	}
	
}
