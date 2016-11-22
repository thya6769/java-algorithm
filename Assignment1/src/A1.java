import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class A1 {
    
    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
    	// long startTime = System.nanoTime();
		WeightedUndirectedGraph<Integer> graph = new WeightedUndirectedGraph<Integer>();
		int n = scan.nextInt();
		int m = scan.nextInt();
		ArrayList<Integer> X = new ArrayList<Integer>();
		scan.nextLine();
		String line = scan.nextLine();
		String[] strs = line.trim().split("\\s+");
		
		for (int i = 0; i < strs.length; i++) {
			X.add(Integer.parseInt(strs[i]));
		}
		
		HashMap<Integer, Node<Integer>> nodes = new HashMap<Integer, Node<Integer>>();
		// add all the nodes
		for(int i = 0; i < n; i++){
			Node<Integer> node = new Node<Integer>(i);
			graph.addNode(node);
			nodes.put(i, node);	
		}
		HashMap<Integer, Double> cheapest = new HashMap<Integer,Double>();
		for(int i = 0; i < X.size(); i++){
			cheapest.put(X.get(i), Double.MAX_VALUE);
		}
		for(int j = 0; j < m; j++){
			Integer x1 = scan.nextInt();
			Integer x2 = scan.nextInt();
			double weight = scan.nextDouble();

			if(X.contains(x1) && X.contains(x2)){
				continue;
			} else if(X.contains(x1)){
				if(weight < cheapest.get(x1)){
					cheapest.put(x1, weight);
				}
				continue;
			} else if(X.contains(x2)){
				if(weight < cheapest.get(x2)){
					cheapest.put(x2, weight);
				}
				continue;
			}
			Node<Integer> node1 = nodes.get(x1);
			Node<Integer> node2 = nodes.get(x2);
	
			graph.addEdge(node1, node2, weight);
			graph.addEdge(node2, node1, weight);

		}

		double sum = 0.0;
		for(Double value : cheapest.values()){
			sum += value;
		}
		System.out.printf("%.2f", graph.findPrimMST(graph,X) + sum);
		System.out.println();
		// long endTime = System.nanoTime();
		// System.out.println("Took "+(endTime - startTime) + " ns"); 
		scan.close();
    }
	
	
}
