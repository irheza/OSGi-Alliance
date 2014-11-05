package peta;

public class Edge {
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

