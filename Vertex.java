package Project5;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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
	

	protected class Edge implements java.io.Serializable {
		private VertexInterface<T> vertex;
		private double weight;
		
	
		protected Edge(VertexInterface<T> endVertex, double edgeWeight){
			vertex = endVertex;
			weight = edgeWeight;
		}
		
		protected VertexInterface<T> getEndVertex(){
			return vertex;
		}
		protected double getWeight(){
			return weight;
		}
	}

	
	private class NeighborIterator implements Iterator<VertexInterface<T>>{

		Iterator<Edge> edgesIterator;
		private NeighborIterator() {
			edgesIterator = edgeList.iterator();
		}
		@Override
		public boolean hasNext() {
			return edgesIterator.hasNext();
		}

		@Override
		public VertexInterface<T> next() {
			VertexInterface<T> nextNeighbor = null;
			if(edgesIterator.hasNext()){
				Edge edgeToNextNeighbor = edgesIterator.next();
				nextNeighbor = edgeToNextNeighbor.getEndVertex();
			}
			else
				throw new NoSuchElementException();
			return nextNeighbor;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	
	private class WeightIterator implements Iterator{
		
		private Iterator<Edge> edgesIterator;
		private WeightIterator(){
			edgesIterator = edgeList.iterator();
		}
		@Override
		public boolean hasNext() {
			return edgesIterator.hasNext();
		}
		@Override
		public Object next() {
			Double result;
			if(edgesIterator.hasNext()){
				Edge edge = edgesIterator.next();
				result = edge.getWeight();
			}
			else throw new NoSuchElementException();
			return (Object)result;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	@Override
	public T getLabel() {
		return label;
	}

	@Override
	public void visit() {
		this.visited = true;
	}

	@Override
	public void unvisit() {
		this.visited = false;
	}

	@Override
	public boolean isVisited() {
		return visited;
	}

	@Override
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

	
	public boolean connect(VertexInterface<T> endVertex) {
		return connect(endVertex, 0);
	}


	public Iterator<VertexInterface<T>> getNeighborIterator() {
		return new NeighborIterator();
	}

	public Iterator getWeightIterator() {
		return new WeightIterator();
	}

	
	public boolean hasNeighbor() {
		return !(edgeList.isEmpty());
	}


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

	
	public void setPredecessor(VertexInterface<T> predecessor) {
		this.previousVertex = predecessor;
	}

	
	public VertexInterface<T> getPredecessor() {
		return this.previousVertex;
	}

	@Override
	public boolean hasPredecessor() {
		return this.previousVertex != null;
	}

	@Override
	public void setCost(double newCost) {
		cost = newCost;
	}

	@Override
	public double getCost() {
		return cost;
	}
	

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
}