package Project5;
import java.util.Iterator;
import ADTPackage.*; // Classes that implement various ADTs
/**
   A class that implements the ADT directed graph.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class DirectedGraph<T> implements GraphInterface<T>
{
	private DictionaryInterface<T, VertexInterface<T>> vertices;
	private int edgeCount;
	
	public DirectedGraph()
	{
		vertices = new LinkedDictionary<>();
		edgeCount = 0;
	} // end default constructor

/* Implementations of the graph operations go here. 
   . . . */
   public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
    resetVertices();
    QueueInterface<T> traversalOrder = new LinkedQueue<>();
    QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
    
    VertexInterface<T> originVertex = vertices.getValue(origin);
    originVertex.visit();
    traversalOrder.enqueue(origin);    // Enqueue vertex label
    vertexQueue.enqueue(originVertex); // Enqueue vertex

    while (!vertexQueue.isEmpty())
    {
        VertexInterface<T> frontVertex = vertexQueue.dequeue();
        Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();

        while (neighbors.hasNext())
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
            {
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexQueue.enqueue(nextNeighbor);
            } // end if
        } // end while
    } // end while

    return traversalOrder;
    } // end getBreadthFirstTraversal

    public int getShortestPath(T begin, T end, StackInterface<T> path)
    {
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        originVertex.visit();

        // Assertion: resetVertices() has executed setCost(0)
        // and setPredecessor(null) for originVertex

        vertexQueue.enqueue(originVertex);
        while (!done && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                } // end if

                if (nextNeighbor.equals(endVertex))
                    done = true;
            } // end while
        } // end while

        // Traversal ends; construct shortest path
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathLength;
    } // end getShortestPath
  
} // end DirectedGraph
