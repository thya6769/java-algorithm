import java.util.List;

public interface WeightedGraph<T> {

    public int size();
    public boolean isEmpty();
    
    public List<Node<T>> getNodes();
    public void addNode(Node<T> n);
    public void removeNode(Node<T> n);
    
    public void addEdge(Node<T> source, Node<T> destination, double weight);
    public double getWeight(Node<T> source, Node<T> destination);
    
} 