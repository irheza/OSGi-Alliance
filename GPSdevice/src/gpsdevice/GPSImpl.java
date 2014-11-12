package gpsdevice;

import java.util.ArrayList;
import java.util.Random;

import peta.Graph;
import peta.Lokasi;
import gps.GPS;
import peta.*;

public class GPSImpl implements GPS {
	Lokasi currentPosition;
	Graph peta;
	public GPSImpl()
	{
		
	}
	@Override
	public String getCurrentPosition() {
		// TODO Auto-generated method stub
		return currentPosition.getKordinat();
	}

	@Override
	public void start() {
		ArrayList<Lokasi> listLokasi = new ArrayList<Lokasi>();
		Lokasi a= new Lokasi("A");
		Lokasi b= new Lokasi("B");
		Lokasi c= new Lokasi("C");
		Lokasi d= new Lokasi("D");
		Lokasi e= new Lokasi("E");
		Lokasi f= new Lokasi("F");
		Lokasi g= new Lokasi("G");
		Lokasi h= new Lokasi("H");
		Lokasi i= new Lokasi("I");
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
		peta.addEdge(a,b);
		peta.addEdge(a,d);
		peta.addEdge(b,e);
		peta.addEdge(c,d);
		peta.addEdge(d,e);
		peta.addEdge(e,f);
		peta.addEdge(f,g);
		peta.addEdge(f,i);
		peta.addEdge(h,i);
		currentPosition = a;

	}

	@Override
	public String move() {
		Random rand = new Random();
		boolean val = rand.nextInt(2)==0;
		//kemungkinan 50% diam ditempat, selain itu bergerak ketempat lain
		if(val==true)
		{
			ArrayList<Lokasi> tetangga= peta.getTetangga(currentPosition);
			currentPosition = tetangga.get(rand.nextInt(tetangga.size()));
		}
		return currentPosition.getKordinat();
	}

	@Override
	public Lokasi getObjectLokasi() {
		// TODO Auto-generated method stub
		return currentPosition;
	}

}
