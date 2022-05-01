package Project5;
/**
   An interface of methods that create, manipulate, and process a graph.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface GraphInterface<T> extends BasicGraphInterface<T>, 
                                           GraphAlgorithmsInterface<T>
{
   /** Performs a breadth-first traversal of this graph.
       @param origin  An object that labels the origin vertex of the traversal.
       @return  A queue of labels of the vertices in the traversal, with
                the label of the origin vertex at the queue's front. */
                public QueueInterface<T> getBreadthFirstTraversal(T origin);

                /** Performs a depth-first traversal of this graph.
                    @param origin  An object that labels the origin vertex of the traversal.
                    @return  A queue of labels of the vertices in the traversal, with
                             the label of the origin vertex at the queue's front. */
                public QueueInterface<T> getDepthFirstTraversal(T origin);
             
                /** Finds the shortest-length path between two given vertices in this graph.
                    @param begin  An object that labels the path's origin vertex.
                    @param end    An object that labels the path's destination vertex.
                    @param path   A stack of labels that is empty initially;
                                  at the completion of the method, this stack contains
                                  the labels of the vertices along the shortest path;
                                  the label of the origin vertex is at the top, and
                                  the label of the destination vertex is at the bottom       
                    @return  The length of the shortest path. */
                public int getShortestPath(T begin, T end, StackInterface<T> path);
             
                /** Finds the least-cost path between two given vertices in this graph.
                    @param begin  An object that labels the path's origin vertex.
                    @param end    An object that labels the path's destination vertex.
                    @param path   A stack of labels that is empty initially;
                                  at the completion of the method, this stack contains
                                  the labels of the vertices along the cheapest path;
                                  the label of the origin vertex is at the top, and
                                  the label of the destination vertex is at the bottom       
                    @return  The cost of the cheapest path. */
                public double getCheapestPath(T begin, T end, StackInterface<T> path);
               
                 /** Adds a given vertex to this graph.
       @param vertexLabel  An object that labels the new vertex and is
                           distinct from the labels of current vertices.
       @return  True if the vertex is added, or false if not. */
   public boolean addVertex(T vertexLabel);

   /** Adds a weighted edge between two given distinct vertices that 
       are currently in this graph. The desired edge must not already 
       be in the graph. In a directed graph, the edge points toward
       the second vertex given.
       @param begin  An object that labels the origin vertex of the edge.
       @param end    An object, distinct from begin, that labels the end
                     vertex of the edge.
       @param edgeWeight  The real value of the edge's weight.
       @return  True if the edge is added, or false if not. */
   public boolean addEdge(T begin, T end, double edgeWeight);

   /** Adds an unweighted edge between two given distinct vertices 
       that are currently in this graph. The desired edge must not
       already be in the graph. In a directed graph, the edge points 
       toward the second vertex given.
       @param begin  An object that labels the origin vertex of the edge.
       @param end    An object, distinct from begin, that labels the end
                     vertex of the edge.
       @return  True if the edge is added, or false if not. */
   public boolean addEdge(T begin, T end);

   /** Sees whether an edge exists between two given vertices.
       @param begin  An object that labels the origin vertex of the edge.
       @param end    An object that labels the end vertex of the edge.
       @return  True if an edge exists. */
   public boolean hasEdge(T begin, T end);

   /** Sees whether this graph is empty.
       @return  True if the graph is empty. */
   public boolean isEmpty();

   /** Gets the number of vertices in this graph.
       @return  The number of vertices in the graph. */
   public int getNumberOfVertices();

   /** Gets the number of edges in this graph.
      @return  The number of edges in the graph. */
   public int getNumberOfEdges();

   /** Removes all vertices and edges from this graph resulting in an empty graph. */
   public void clear();

} // end GraphInterface
