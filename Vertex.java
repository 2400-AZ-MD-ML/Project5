package Project5;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 A class of vertices for a graph.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
class Vertex<T> implements VertexInterface<T>, java.io.Serializable {

	private T label;
	private List<Edge> edgeList;
	private boolean visited;
	private VertexInterface<T> previousVertex;
	private double cost;
	
	public Vertex(T vertexLabel){
		label = vertexLabel;
		edgeList = new LinkedList<Edge>();
		visited = false;
		previousVertex = null;
		cost = 0;
	}
	//Provided class
	protected class Edge implements java.io.Serializable {
		private VertexInterface<T> vertex;
		private double weight;
		
	
		protected Edge(VertexInterface<T> endVertex, double edgeWeight){
			vertex = endVertex;
			weight = edgeWeight;
		} // end constructor
		protected Edge(VertexInterface<T> endVertex)
		{
		   vertex = endVertex;
		   weight = 0;
		} // end constructor
		
		protected VertexInterface<T> getEndVertex(){
			return vertex;
		}
		protected double getWeight(){
			return weight;
		}
	}
	/* Implementations of the vertex operations go here.
   . . . */
   //Returns label
   public T getLabel() {
	return label;
}

//Changes visited for a vertex to true
public void visit() {
	visited = true;
}

//Changes visited for a vertex to false
public void unvisit() {
	visited = false;
}

//Returns if the vertex was visited
public boolean isVisited() {
	return visited;
}
	
	//Makes a connection for the end vertex
	public boolean connect(VertexInterface<T> endVertex) {
		return connect(endVertex, 0);
	}

		//provided
	public Iterator<VertexInterface<T>> getNeighborIterator() {
		return new NeighborIterator();
	}
		//returns an instance of the privateinner class WeightIterator
	public Iterator getWeightIterator() {
		return new WeightIterator();
	}

		//provided
	public boolean hasNeighbor() {
		return !(edgeList.isEmpty());
	}
		//returns the previousVertex value
	public VertexInterface<T> getPredecessor() {
		return previousVertex;
	}

		//Returns whether the previousVertex value was null or not
	public boolean hasPredecessor() {
		return previousVertex != null;
	}

		//changes the cost
	public void setCost(double newCost) {
		cost = newCost;
	}

		//returns the cost
	public double getCost() {
		return cost;
	}
	
		//provided
	public boolean equals(Object other){
		boolean result;
		if((other == null) || (getClass() != other.getClass()))
			result = false;
		else
		{
			Vertex<T> otherVertex = (Vertex<T>)other;
			result = label.equals(otherVertex.label);
		}
		return result;
	}
	//provided
	public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
	
		boolean result = false;
		if(!this.equals(endVertex)){
			Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
			boolean duplicateEdge = false;
			while(!duplicateEdge && neighbors.hasNext()){
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(endVertex.equals(nextNeighbor)){
					duplicateEdge = true;
					break;
				}
			}//end while
			if(!duplicateEdge){
				edgeList.add(new Edge(endVertex, edgeWeight));
				result = true;
			}//end if
		}//end if
		return result;
	}

		//provided
	public VertexInterface<T> getUnvisitedNeighbor() {
		VertexInterface<T> result = null;
		Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
		while(neighbors.hasNext() && result == null){
			VertexInterface<T> nextNeighbor = neighbors.next();
			if(!nextNeighbor.isVisited())
				result = nextNeighbor;
		}
		return result;
	}

		//Updates/changes the previousVertex
	public void setPredecessor(VertexInterface<T> predecessor) {
		previousVertex = predecessor;
	}
		//provided
	private class NeighborIterator implements Iterator<VertexInterface<T>>{

		Iterator<Edge> edges;
		private NeighborIterator() {
			edges = edgeList.iterator();
		}
	
		public boolean hasNext() {
			return edges.hasNext();
		}

		
		public VertexInterface<T> next() {
			VertexInterface<T> nextNeighbor = null;
			if(edges.hasNext()){
				Edge edgeToNextNeighbor = edges.next();
				nextNeighbor = edgeToNextNeighbor.getEndVertex();
			}
			else
				throw new NoSuchElementException();
			return nextNeighbor;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
		//Inner class based off NeighborIterator
	private class WeightIterator implements Iterator{
		
		private Iterator<Edge> edgesIterator;
		//Constructor
		private WeightIterator(){
			edgesIterator = edgeList.iterator();
		}

		public boolean hasNext() {
			return edgesIterator.hasNext();
		}
		//Returns the object of the next object if it has one
		public Object next() {
			Double result;
			if(edgesIterator.hasNext()){
				Edge edge = edgesIterator.next();
				result = edge.getWeight();
			}
			else throw new NoSuchElementException();
			return (Object)result;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	

	



	
	

	
	
}