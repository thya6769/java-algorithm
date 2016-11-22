import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedUndirectedGraph<T> implements WeightedGraph<T> {
	private List<Node<T>> nodes;
    private List<List<Double>> edges;
  
    public WeightedUndirectedGraph(){
    	nodes = new ArrayList<Node<T>>();
    	edges = new ArrayList<List<Double>>();
    }
	@Override
	public int size() {
		return nodes.size();
	}

	@Override
	public boolean isEmpty() {
		if(size() == 0){
			return true;
		}
		return false;
	}

	@Override
	public List<Node<T>> getNodes() {
		return nodes;
	}
	public Node<T> getNode(T value){
		Node<T> node = new Node<T>(value);
		for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).equals(node)){
				return node;
			}
		}
		return null;
	}

	@Override
	public void addNode(Node<T> n) {
		nodes.add(n);
		for(List<Double> row : edges){
			row.add(0.0);
		}
		List<Double> row = new ArrayList<Double>();
		edges.add(row);
		for(Node<T> node : nodes){
			row.add(0.0);
		}
	}

	@Override
	public void removeNode(Node<T> node) {
		int index = this.nodes.indexOf(node);
		nodes.remove(index);
		edges.remove(index);
		for(List<Double> row : edges){
			row.remove(index);
		}
	}

	@Override
	public void addEdge(Node<T> source, Node<T> destination, double weight) {
		int sourceIndex = this.nodes.indexOf(source);
		int destinationIndex = this.nodes.indexOf(destination);
		edges.get(sourceIndex).set(destinationIndex, weight);
	}

	@Override
	public double getWeight(Node<T> source, Node<T> destination) {
		int sourceIndex = this.nodes.indexOf(source);
		int destinationIndex = this.nodes.indexOf(destination);
		return edges.get(sourceIndex).get(destinationIndex);
	}
	
	public double findPrimMST(WeightedUndirectedGraph<T> graph, List<Integer> X){
		// stores distance of each node
		// initially this is 0 for first node
		// maximum for other nodes
		double[] distance = new double[size()];
		boolean first = true;
		for(int i = 0; i < size();i++){
			if(!X.contains(i) && first) {
				distance[i] = 0.0;
				first = false;
			} else {
				distance[i] = Integer.MAX_VALUE;
			}
		}
		WeightedUndirectedGraph<T> mst = new WeightedUndirectedGraph<T>();
				
		// store the edge as key
		// node index as value
		ArrayPriorityQueue<Double,Integer> queue = new ArrayPriorityQueue<Double,Integer>();
		
		for(int i = 0; i < nodes.size();i++){
			queue.insert(distance[i], i);
		}
		first = true;
		double sum = 0.0;
		while(!queue.isEmpty()){
			int currentIndex = queue.removeMin();
			mst.addNode(nodes.get(currentIndex)); // add the first node to mst.
			int cheapestIndex = 0;
			if(!first){
				// we have to get the cheapest index of the node that is connected with currentIndex node
				for(int i = 0; i < size(); i++){
					double edgeWeight = edges.get(i).get(currentIndex);
					if(edgeWeight == distance[currentIndex]){
						cheapestIndex = i;
					}
				}
				if(X.contains(cheapestIndex) || X.contains(currentIndex)){
					;
				}else{
					sum += distance[currentIndex];
					mst.addEdge(nodes.get(cheapestIndex), nodes.get(currentIndex), distance[currentIndex]);
				}
			}
			// connect vertex u to mst using edge e
			// loop through all the neighbours of the currentIndex
			for(int neighbourIndex = 0; neighbourIndex < size(); neighbourIndex++){
				double edgeWeight = edges.get(currentIndex).get(neighbourIndex);
				// if edgeWeight is less than current shortest distance update it
				if(edgeWeight < distance[neighbourIndex] && edgeWeight != 0){
        			double oldDistance = distance[neighbourIndex];
        			distance[neighbourIndex] = edgeWeight;
        			// replace the queue value with new distance.
		       		queue.replaceKey(oldDistance, neighbourIndex, distance[neighbourIndex]);
				}
			}
			first = false;
		}
//		System.out.println(sum);
		return sum;
	}
	
}
