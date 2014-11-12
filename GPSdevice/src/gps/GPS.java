package gps;

import peta.Lokasi;

public interface GPS {
	public String getCurrentPosition();
	public void start();
	public String move();
	public Lokasi getObjectLokasi();
}
