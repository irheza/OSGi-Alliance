package preferencerepository;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Interface for services.
 *
 * @author Natanael Taufik
 */
public interface PreferenceRepositoryServices {
	
	public static boolean thereIsUser = false;

	/**
	 * Gets the suggested service of this query.
	 *
	 * @param name the name
	 * @param time the time
	 * @param weather the weather
	 * @param temperature the temperature
	 * @param location the location
	 * @return the suggested service of this query
	 */
	public ArrayList<String> getSuggestedServiceOfThisQuery(String name, String time, String weather, int temperature, String location);

	public boolean isValidUser();
}
