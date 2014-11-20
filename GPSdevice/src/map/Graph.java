package map;

import java.util.ArrayList;

public class Graph {
	ArrayList<Location> vertex;
	ArrayList<Edge> listedge;
	
	public Graph(ArrayList<Location> vertex)
	{
		this.vertex = vertex;
		listedge = new ArrayList<Edge>();
	}
	
	public void addEdge(Location a, Location b)
	{
		Edge tmp = new Edge(a,b);
		listedge.add(tmp);
	}
	
	public ArrayList<Edge> getListEdge()
	{
		return listedge;
	}
	
	public ArrayList<Location> getListLokasi()
	{
		return vertex;
	}
	
	public ArrayList<Location> getTetangga(Location input)
	{
		ArrayList<Location> tetangga = new ArrayList<Location>();
		for(Edge edge : listedge)
		{
			Location a = edge.getLeft();
			Location b = edge.getRight();
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
	public Location left;
	public Location right;
	
	public Edge(Location left, Location right){
		this.left = left;
		this.right = right;
		
	}
	
	public Location getLeft()
	{
		return this.left;
	}
	
	public Location getRight()
	{
		return this.right;
	}

}

