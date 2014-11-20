package preferencerepository;

import java.util.ArrayList;

/**
 * Interface for services
 * 
 * @author Natanael Taufik
 *
 */
public interface PreferenceRepositoryServices {
	public ArrayList<String> getSuggestedServiceOfThisQuery(String name, String time, String weather, int temperature, String location);
}
