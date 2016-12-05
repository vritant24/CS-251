import java.awt.Point;
import java.util.Iterator;


/*		Part 2
 * 
 * 		TODO: Implement the following methods
 *              
 *      maxInDegree
 *      maxOutDegree
 *      hasOneCycle 
 * 		numEdgeTriangles
 * 		numTriangles
 * 		vertexClusterCoeff
 * 		globalClusterCoeff
 * 
 */
public class Part2 {
	

	// TODO:
	// Returns the maximum in-degree in the graph G
	public int maxInDegree(Graph G)
	{
		int length = G.getNumVertices();
		int[] array = new int[length];
		
		Iterator<Integer> iterator;
		
		for(int i = 0; i < length; i++) {
			iterator = G.getAdjacentVertices(i).iterator();
			while(iterator.hasNext()) {
				array[iterator.next()]++;
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < length; i++) {
			if(array[i] > max) {
				max = array[i];
			}
		}
		
		return max;
	}
	
	
	
	// TODO:
	// Returns the maximum out degree in the graph G
	public int maxOutDegree(Graph G)
	{
		int length = G.getNumVertices();
		int max = 0;
		int tempSize;
		
		for(int i = 0; i < length; i++) {
			tempSize = G.getAdjacentVertices(i).size();
			if(tempSize > max) {
				max = tempSize;
			}
		}
		
		return max;
	}

	
	
	// TODO:
	// Determines if a graph has only one cycle
	// Returns a linked list containing the vertices in the cycle if there was only one cycle
	// Returns an empty linked list if there are none or more than one cycle
	public LinkedList<Integer> hasOneCycle(Graph G)
	{
		return new LinkedList<Integer>();
	}

	// TODO:
	// Returns the total number of triangles in the entire graph G that contain the edge (u,v)
	public int numEdgeTriangles(Graph G, int u, int v)
	{
		if(!G.hasEdge(u, v)) {
			return 0;
		}
		int counter = 0;
		LinkedList<Integer> listU = G.getAdjacentVertices(u);
		LinkedList<Integer> listV = G.getAdjacentVertices(v);
		Iterator<Integer> iterator = listU.iterator();
		
		while(iterator.hasNext()) {
			if(listV.contains(iterator.next())) {
				counter++;
			}
		}
		
		return counter;
	}
	

	// TODO:
	// Returns the total number of triangles in the graph
	public int numTriangles(Graph G)
	{
		int length = G.getNumVertices();
		int count = 0;
		
		for(int i = 0; i < length; i++) {
			for(int j = i; j < length; j++) {
				if(!G.hasEdge(i, j)) {
					continue;
				}
				for(int k = j; k < length; k++) {
					if(G.hasEdge(i, k) && G.hasEdge(j, k)) {
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	// TODO:
	// Returns the vertex clustering coefficient of v
	// The vertex clustering coefficient is the fraction of pairs of vertices
	// that are adjacent to v that are also adjacent to each other.
	public double vertexClusterCoeff(Graph G, int v)
	{
		return 0.0;
	}
	

	// TODO:
	// Returns the average of all n vertices clustering coefficients
	public double globalClusterCoeff(Graph G)
	{
		return 0.0;
	}
	
	
	
}
