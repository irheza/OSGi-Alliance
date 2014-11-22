package map;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Graph.
 */
public class Graph {
	
	/** The vertex. */
	ArrayList<Location> vertex;
	
	/** The listedge. */
	ArrayList<Edge> listedge;
	
	/**
	 * Instantiates a new graph.
	 *
	 * @param vertex the vertex
	 */
	public Graph(ArrayList<Location> vertex)
	{
		this.vertex = vertex;
		listedge = new ArrayList<Edge>();
	}
	
	/**
	 * Adds the edge.
	 *
	 * @param a the a
	 * @param b the b
	 */
	public void addEdge(Location a, Location b)
	{
		Edge tmp = new Edge(a,b);
		listedge.add(tmp);
	}
	
	/**
	 * Gets the list edge.
	 *
	 * @return the list edge
	 */
	public ArrayList<Edge> getListEdge()
	{
		return listedge;
	}
	
	/**
	 * Gets the list lokasi.
	 *
	 * @return the list lokasi
	 */
	public ArrayList<Location> getListLokasi()
	{
		return vertex;
	}
	
	/**
	 * Gets the tetangga.
	 *
	 * @param input the input
	 * @return the tetangga
	 */
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

/**
 * The Class Edge.
 */
class Edge {
	
	/** The left. */
	public Location left;
	
	/** The right. */
	public Location right;
	
	/**
	 * Instantiates a new edge.
	 *
	 * @param left the left
	 * @param right the right
	 */
	public Edge(Location left, Location right){
		this.left = left;
		this.right = right;
		
	}
	
	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public Location getLeft()
	{
		return this.left;
	}
	
	/**
	 * Gets the right.
	 *
	 * @return the right
	 */
	public Location getRight()
	{
		return this.right;
	}

}

