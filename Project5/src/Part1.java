import java.util.*;
import java.lang.*;

/*		Part 1
 * 
 * 		TODO: Implement the following method stubs
 * 		You must submit this file
 *
 * 		medianDegree
 * 		hasGiantConnectedComponent
 * 		is5Clique
 * 		has6DegreeRootN
 * 		has6Degree6
 */
public class Part1 {


	// TODO:
	// Find the median degree of all vertices in G
	public double medianDegree(Graph G)
	{	
		double[] track = new double[G.getNumVertices()];
		
		int counter;
		for(int i = 0; i < G.getNumVertices(); i++) {
			counter = 0;
			for(int j = 0; j < G.numVertices; j++) {
				if(i == j) {
					continue;
				}
				if(G.hasEdge(i, j)) {
					counter++;
				}
			}
			track[i] = counter;
		}
		
		Arrays.sort(track);
		if(track.length % 2 == 1) {
			return track[track.length / 2];
		}
		else {
			return ((track[track.length / 2] + track[(track.length / 2) + 1]) / 2);
		}
	}
	
	
	// TODO:
	// Returns true if G has a connected component of size greater than n/2
	// Returns false otherwise
	public LinkedList<Integer> hasGiantConnectedComponent(Graph G)
	{
		return new LinkedList<Integer>();
	}
	
	
	
	// TODO:
	// Returns true if the given 5 vertices in the list form a clique
	// Returns false otherwise
	public boolean is5Clique(Graph G, LinkedList<Integer> vertices)
	{
		return false;
	}
	
	
	
	// TODO:
	// If the given graph has at least 6 vertices of degree sqrt(n) returns 6 such vertices
	// Return an empty list otherwise
	public LinkedList<Integer> has6DegreeRootN(Graph G)
	{
		return new LinkedList<Integer>();
	}
	
	
	
	// TODO:
	// If the given graph has at least 6 vertices of degree 6 returns 6 such vertices
	// Return an empty list otherwise
	public LinkedList<Integer> has6Degree6(Graph G)
	{
		return new LinkedList<Integer>();
	}
}
