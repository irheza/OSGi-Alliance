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
	String flag = "init";
	public String [] suggestedPlace;
	
	/** The time. */
	private String time = "";
	
	/** The current location. */
	private String currentLocation = "";
	
	/** The current user .*/
	public String deviceUser="";
	private boolean thereIsUser=false;
	
	public BundleContext context;
	
	
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
		this.context =context;
		prefRepoServices = (PreferenceRepositoryServices) context.getService(preferencesRepoReference);
	}
	
	/* (non-Javadoc)
	 * @see contextmanager.ContextManager#sendSuggestion()
	 */
	@Override
	public void sendSuggestion(String whichContext) {
			System.out.println("");
			System.out.println("Selamat datang di "+ this.currentLocation);
			System.out.println("Waktu menunjukkan pukul "+ this.time);
			System.out.println("Cuaca saat ini adalah "+ this.cuaca+" dengan suhu "+ this.suhu+ " derajat celcius");
			ArrayList<String> serviceSuggested = prefRepoServices
				.getSuggestedServiceOfThisQuery(this.deviceUser, this.time, this.cuaca, this.suhu, this.currentLocation);
			
			if(!serviceSuggested.isEmpty()){
				try {
					setFlag("preference");
					checkSuggestionPlace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		if(!placeName.isEmpty())
		printSuggestionMessage(placeName);	
	}
	
	private boolean checkServicePlace(PlaceOfInterest place){
		ArrayList<String> serviceOfferList =  place.getService();
		String[] serviceList =  new String[serviceOfferList.size()];
		serviceList = serviceOfferList.toArray(serviceList);
		
		ArrayList<String> serviceSuggested = prefRepoServices
				.getSuggestedServiceOfThisQuery(this.deviceUser, this.time, this.cuaca, this.suhu, this.currentLocation);
		String[] serviceWanted =  new String[serviceSuggested.size()];
		serviceWanted = serviceSuggested.toArray(serviceList);
		
		ArrayList<String> compareList = new ArrayList<String>();
		if(!serviceOfferList.isEmpty()){
			for(String s : serviceList){
				for(String x: serviceWanted){
					if(s.equals(x)){
						compareList.add(s);
						if(!compareList.isEmpty())  return true;
					}
				}
			}
		}
		
		return false;
	}
	
	//Fungsi ini akan mencetak tempat-tempat yang disarankan
	//Fungsi ini juga akan menyimpan tempat-tempat yang disarankan sehingga
	//saat dipanggil oleh MainMenu dimana flagnya sudah diubah, suggestedPlace sudah
	//memiliki nilai
	private void printSuggestionMessage(ArrayList<String> suggestionList) throws IOException{
		//Hanya tercetak jika user berada pada menu utama
		if(!flag.equals("menu")){
			String[] suggested =  new String[suggestionList.size()];
			suggested = suggestionList.toArray(suggested);
			System.out.println(this.flag);
			System.out.println("Berdasarkan preferensi anda, tempat-tempat berikut menarik untuk dikunjungi. Silahkan pilih salah satu untuk mendapatkan informasi lebih lanjut:");
			int choice = 1;
			for(String s : suggested){
				System.out.println(choice+". "+s);
				choice++;
			}
			this.suggestedPlace = suggested;
		}
	}
	
	@Override
	public String getFlag() {
		// TODO Auto-generated method stub
		return flag;
	}

	@Override
	public void setFlag(String flag) {
		// TODO Auto-generated method stub
		this.flag= flag;
		
	}

	@Override
	public String[] getSuggestedPlace() {
		// TODO Auto-generated method stub
		return suggestedPlace;
	}

	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return this.deviceUser;
	}

	@Override
	public void setUser(String user) {
		// TODO Auto-generated method stub
		this.deviceUser = user;
	}
	
	
	public void checkValidUser(String name){
		this.deviceUser = name;
		ArrayList<String> serviceSuggested = prefRepoServices
				.getSuggestedServiceOfThisQuery(this.deviceUser, this.time, this.cuaca, this.suhu, this.currentLocation);
			this.thereIsUser = prefRepoServices.isValidUser();
	}
	
	public boolean isValidUser(){
		return this.thereIsUser;
	}
	
	public String getValidUser(){
		return this.deviceUser;
	}
	
	public String getCurrentLocation(){
		return this.currentLocation;
	}
}
