package map;

import java.util.ArrayList;

public class Graph {
	ArrayList<Lokasi> vertex;
	ArrayList<Edge> listedge;
	
	public Graph(ArrayList<Lokasi> vertex)
	{
		this.vertex = vertex;
		listedge = new ArrayList<Edge>();
	}
	
	public void addEdge(Lokasi a, Lokasi b)
	{
		Edge tmp = new Edge(a,b);
		listedge.add(tmp);
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

class Edge {
	public Lokasi left;
	public Lokasi right;
	
	public Edge(Lokasi left, Lokasi right){
		this.left = left;
		this.right = right;
		
	}
	
	public Lokasi getLeft()
	{
		return this.left;
	}
	
	public Lokasi getRight()
	{
		return this.right;
	}

}

