package sensor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class SensorImpl.
 */
public class SensorImpl implements Sensor {

	/** The list cuaca. */
	public String[] listCuaca= {"cerah","berawan","hujan"};
	
	/* (non-Javadoc)
	 * @see sensor.Sensor#getSuhu()
	 */
	@Override
	public int getSuhu() {
		return randInt(0,30);
	}
	
	/* (non-Javadoc)
	 * @see sensor.Sensor#getCuaca()
	 */
	@Override
	public String getCuaca() {
		return listCuaca[randInt(0,2)];
	}
	
	/**
	 * Rand int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

	/* (non-Javadoc)
	 * @see sensor.Sensor#getTime()
	 */
	@Override
	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		
		return df.format(calendar.getTime());
	}
}
