package Project5;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
public class GraphTest {
    @Test
    public void bfsTraversalOrderTest(){
        GraphInterface<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
		graph.addVertex("C");
        graph.addVertex("D");
		graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
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
        QueueInterface<String> bfsTraversalOrder = graph.getBreadthFirstTraversal("A");
        String res = "";
        while(!bfsTraversalOrder.isEmpty()){
            res += bfsTraversalOrder.dequeue();
        }
        assertEquals("ABDEGFHCI", res);
    }
      
    @Test
    public void dfsTraversalOrderTest(){
        GraphInterface<String> graph = new DirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
		graph.addVertex("C");
        graph.addVertex("D");
		graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
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
        QueueInterface<String> dfsTraversalOrder = graph.getDepthFirstTraversal("A");
        String res = "";
        while(!dfsTraversalOrder.isEmpty()){
            res += dfsTraversalOrder.dequeue();
        }
        assertEquals("ABEFCHIDG", res);  
    }
  

}
