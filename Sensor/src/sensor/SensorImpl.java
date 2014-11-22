package sensor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class SensorImpl implements Sensor {

	public String[] listCuaca= {"cerah","berawan","hujan"};
	
	@Override
	public int getSuhu() {
		return randInt(0,30);
	}
	
	@Override
	public String getCuaca() {
		return listCuaca[randInt(0,2)];
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
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
