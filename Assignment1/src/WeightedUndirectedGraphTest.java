import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class WeightedUndirectedGraphTest {

	@Test
	public void testConstruction() {
		WeightedUndirectedGraph<String> graph = new WeightedUndirectedGraph<String>();
		assertEquals(0, graph.size());
		assertTrue(graph.isEmpty());
		assertEquals(Arrays.asList(), graph.getNodes());
	}

	@Test
	public void testSize() {
		WeightedUndirectedGraph<String> graph = new WeightedUndirectedGraph<String>();
		Node<String> a = new Node<String>("a");
		Node<String> b = new Node<String>("b");
		Node<String> c = new Node<String>("c");
		graph.addNode(a);
		assertEquals(1, graph.size());
		graph.addNode(b);
		assertEquals(2, graph.size());
		graph.addNode(c);
		assertEquals(3, graph.size());
		graph.removeNode(a);
		assertEquals(2, graph.size());
	}

	@Test
	public void testIsEmpty() {
		WeightedUndirectedGraph<String> graph = new WeightedUndirectedGraph<String>();
		Node<String> a = new Node<String>("a");
		Node<String> b = new Node<String>("b");
		Node<String> c = new Node<String>("c");
		graph.addNode(a);
		assertEquals(1, graph.size());
		graph.addNode(b);
		assertEquals(2, graph.size());
		graph.addNode(c);
		assertEquals(3, graph.size());
		graph.removeNode(a);
		graph.removeNode(b);
		graph.removeNode(c);
		assertTrue(graph.isEmpty());
	}

	@Test
	public void testPrim() {
		WeightedUndirectedGraph<String> g = new WeightedUndirectedGraph<String>();
		Node<String> A = new Node("A");
		Node<String> B = new Node("B");
		Node<String> C = new Node("C");
		Node<String> D = new Node("D");
		Node<String> E = new Node("E");
		Node<String> F = new Node("F");

		g.addNode(A);
		g.addNode(B);
		g.addNode(C);
		g.addNode(D);
		g.addNode(E);
		g.addNode(F);

		g.addEdge(A, B, 2.0);
		g.addEdge(A, C, 8.0);
		g.addEdge(A, E, 7.0);
		g.addEdge(B, C, 5.0);
		g.addEdge(B, D, 7.0);
		g.addEdge(C, D, 9.0);
		g.addEdge(D, F, 4.0);
		g.addEdge(F, E, 3.0);
		g.addEdge(C, F, 8.0);
		/*
		g.addEdge(B, A, 2);
		g.addEdge(C, A, 8);
		g.addEdge(E, A, 7);
		g.addEdge(C, B, 5);
		g.addEdge(D, B, 7);
		g.addEdge(D, C, 9);
		g.addEdge(F, D, 4);
		g.addEdge(E, F, 3);
		g.addEdge(F, C, 8);
*/		
//		WeightedUndirectedGraph<String> mst = g.findPrimMST(g);

//		assertEquals(6, mst.getNodes().size());
//		assertEquals(2, mst.getWeight(A, B), 0.00001);
//		assertEquals(5, mst.getWeight(B, C), 0.00001);
//		assertEquals(7, mst.getWeight(B, D), 0.00001);
//		assertEquals(0, mst.getWeight(C, D), 0.00001);
//		assertEquals(4, mst.getWeight(D,F), 0.00001);
//		assertEquals(3, mst.getWeight(F, E), 0.00001);
	}

}
