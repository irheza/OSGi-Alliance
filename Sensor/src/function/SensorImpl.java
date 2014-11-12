package function;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import sensor.Sensor;

public class SensorImpl implements Sensor {

	public String[] listCuaca= {"cerah","berawan","hujan"};
	
	@Override
	public int getSuhu() {
		return randInt(0,30);
	}
	
	@Override
	public String getCuaca() {
		System.out.println("hello");
		return listCuaca[randInt(0,2)];
	}
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	@Override
	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		
		return df.format(calendar.getTime());
	}

}
