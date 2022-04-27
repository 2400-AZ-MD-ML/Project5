package Project5;
public class Driver {
    public static void main(String[] args) {
        GraphInterface<String> graph = new DirectedGraph<>();
		System.out.println("Graph is empty? " + graph.isEmpty());
		
		System.out.println("Adding vertexs...");
		graph.addVertex("A");
        graph.addVertex("B");
		graph.addVertex("C");
        graph.addVertex("D");
		graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
       
		System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());//5
		

		System.out.println("Adding edges...");
		graph.addEdge("A", "B");
        graph.addEdge("A", "D");
		graph.addEdge("A", "E");
        graph.addEdge("B", "E");
		graph.addEdge("C", "B");
        graph.addEdge("D", "G");
        graph.addEdge("E", "F");
        graph.addEdge("E", "H");
        graph.addEdge("F", "C");
        graph.addEdge("F", "H");
        graph.addEdge("G", "H");
		graph.addEdge("H", "I");
        graph.addEdge("I", "F");
		System.out.println("Breadth First traverse graph with initial vertex 'A'...");
		QueueInterface<String> bfsTraversalOrder = graph.getBreadthFirstTraversal("A");//A D C B E
		while(!bfsTraversalOrder.isEmpty())
			System.out.print(bfsTraversalOrder.dequeue() + " ");
    
		
		System.out.println("\nDFS traverse graph with inital vertex 'A'...");
		QueueInterface<String> dfsTraversalOrder = graph.getDepthFirstTraversal("A");
		while(!dfsTraversalOrder.isEmpty())
			System.out.print(dfsTraversalOrder.dequeue() + " ");
		

		
	
    }
}
