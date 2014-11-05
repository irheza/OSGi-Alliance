package peta;

import java.util.ArrayList;

public class Graph {
	ArrayList<Lokasi> vertex;
	ArrayList<Edge> listedge;
	
	public Graph(ArrayList<Lokasi> vertex)
	{
		this.vertex = vertex;
	}
	
	public void addEdge(Edge x)
	{
		listedge.add(x);
	}
	
	public ArrayList<Edge> getListEdge()
	{
		return listedge;
	}
	
	public ArrayList<Lokasi> getListLokasi()
	{
		return vertex;
	}
	
	public ArrayList<Lokasi> getTetangga(Lokasi input)
	{
		ArrayList<Lokasi> tetangga = new ArrayList<Lokasi>();
		for(Edge edge : listedge)
		{
			Lokasi a = edge.getLeft();
			Lokasi b = edge.getRight();
			if(input.getKordinat().equals(a.getKordinat()) )
			{
				tetangga.add(b);
			}
			else if(input.getKordinat().equals(b.getKordinat()))
			{
				tetangga.add(a);
			}
		}
		return tetangga;
	}
}

