package preferencerepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.osgi.service.prefs.PreferencesService;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Class implementation of PreferenceRepository
 * this class implement service for calling bundles
 * 
 * @author Natanael Taufik
 *
 */
public class PreferenceRepositoryServicesImpl implements PreferenceRepositoryServices {
	
	private PreferencesService service;
	private Preferences preferences;
	
	/**
	 * This method read preferences from file and input them to preferences
	 * 
	 * Root node is empty string
	 * Root node's child is user's name
	 * user's name properties is their preferences
	 */
	public void initPreferences(ServiceTracker tracker) throws Exception {
		// Get service's reference
		service = (PreferencesService) tracker.getService();
		// Get root node of preferences
		preferences = service.getSystemPreferences();
		
		File fl = new File("users_preferences.txt");
		if (fl.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(fl));
			String ln;
			Preferences newNode = preferences.node("");
			while ((ln = br.readLine()) != null) {
				String[] words = ln.split(" ");
				if (words[0].equals("name:")) {
					if (preferences.nodeExists(words[1])) {
						System.out.println("Warning::DuplicateNode:: You edited node " + words[1] + " childs");
					}
					newNode = preferences.node(words[1]);
					//System.out.println("namanya :"+newNode.name()); // Debugging purpose
				}
				else if(words[0].substring(0,4).equals("pref")) {
					if (newNode.get(words[2], "") != "") {
						System.out.println("Warning::DuplicateNode:: You edited node " + words[2] + " childs");
					}
					newNode.put(words[2], words[4]);
					//System.out.print("if then "+newNode.get(words[2], words[4])); // Debugging purpose
				}
			}
			br.close();
		} else {
			String workingDir = System.getProperty("user.dir");
			System.out.println("Error::type::FileIO: File not found on Activator.java of PreferenceRepository.");
			System.out.println("Your current working directory is: " + workingDir);
			System.out.println("Make sure your required file is in the directory.");
		}
	}
	
	/**
	 * Service for calling bundles
	 * This method process query and determine the output based on user's preferences
	 * 
	 * @param name the name of user, time current time, weather current weather
	 * temperature current temperature, location current location
	 * @return ArrayList of string, consist of preferred service from IoI based on preferences data
	 */
	@Override
	public ArrayList<String> getSuggestedServiceOfThisQuery(String name,
			String time, String weather, int temperature, String location) {
		ArrayList<String> ret = new ArrayList<String>();
		
		String[] temp = new String[5];
		temp[0] = name;
		temp[1] = time;
		temp[2] = weather;
		temp[3] = Integer.toString(temperature);
		temp[4] = location;
		
		ArrayList<String> notDistinct = new ArrayList<String>();
		try {
			if (preferences.nodeExists(temp[0])) {
				Preferences parentNode = preferences.node(temp[0]);
				if (parentNode.get(temp[1], "") != "") {
					notDistinct.add(parentNode.get(temp[1], ""));
				}
				if (parentNode.get(temp[2], "") != "") {
					notDistinct.add(parentNode.get(temp[2], ""));
				}
				if (parentNode.get(temp[3], "") != "") {
					notDistinct.add(parentNode.get(temp[3], ""));
				}
				if (parentNode.get(temp[4], "") != "") {
					notDistinct.add(parentNode.get(temp[4], ""));
				}
			} else {
				System.out.println("Your requested user doesn't has preferences yet, please contact our administrator.");
			}
		} catch (BackingStoreException e1) {
			e1.printStackTrace();
		}
		
		if (notDistinct.size() != 0) {
			for (int i = 0; i < notDistinct.size(); i++) {
				if (!ret.contains(notDistinct.get(i))) {
					ret.add(notDistinct.get(i));
				}
			}
		}

		return ret;
	}

	/**
	 * Clean service
	 */
	public void cleanPreferencesService() {
		service = null;
	}

	/**
	 * Check whether a string is an integer
	 * 
	 * @param s string input
	 * @return true if an integer, false if a string
	 */
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
}
