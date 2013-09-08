package algorithms;

import data.graph.Node;
import data.graph.Graph;

public class GraphAlgos {
    public static void main (String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        
        System.out.println("Test graph creation");
        Graph g1 = new Graph();
        g1.addNodes (new Node[]{n0,n1,n2,n3,n4,n5,n6,n7,n8,n9});
        System.out.println (g1);
        g1.addEdge (n0,n1,(int)(Math.random()*10));
        g1.addEdge (n1,n2,(int)(Math.random()*10));
        g1.addEdge (n2,n0,(int)(Math.random()*10));
        g1.addEdge (n2,n3,(int)(Math.random()*10));
        g1.addEdge (n2,n4,(int)(Math.random()*10));
        g1.addEdge (n2,n5,(int)(Math.random()*10));
        g1.addEdge (n3,n0,(int)(Math.random()*10));
        g1.addEdge (n4,n5,(int)(Math.random()*10));
        g1.addEdge (n4,n6,(int)(Math.random()*10));
        g1.addEdge (n4,n7,(int)(Math.random()*10));
        g1.addEdge (n5,n3,(int)(Math.random()*10));
        g1.addEdge (n5,n7,(int)(Math.random()*10));
        g1.addEdge (n5,n8,(int)(Math.random()*10));
        g1.addEdge (n6,n1,(int)(Math.random()*10));
        g1.addEdge (n6,n7,(int)(Math.random()*10));
        g1.addEdge (n7,n8,(int)(Math.random()*10));
        g1.addEdge (n7,n9,(int)(Math.random()*10));
        g1.addEdge (n8,n3,(int)(Math.random()*10));
        g1.addEdge (n8,n9,(int)(Math.random()*10));
        g1.addEdge (n9,n7,(int)(Math.random()*10));
        g1.addEdge (n9,n0,(int)(Math.random()*10));
        System.out.println (g1);
        g1.removeAnyEdge (n9,n0);
        System.out.println (g1);
        System.out.println ();

        System.out.println("Test depth first search (random root)");
        System.out.println (depthFirstSearch(g1,5));
        System.out.println (depthFirstSearch(g1,3));
        System.out.println (depthFirstSearch(g1,2));
        System.out.println ();

        System.out.println("Test depth first search (fixed root) [private method]");
        Node n = g1.getRandomNode();
        System.out.println (n);
        System.out.println (depthFirstSearch(n,5));
        g1.flushFlags();
        System.out.println (depthFirstSearch(n,3));
        g1.flushFlags();
        System.out.println (depthFirstSearch(n,2));
        g1.flushFlags();
        System.out.println ();
        
        
        
    }
    
    public static Node depthFirstSearch (Graph g, int val) {
        Node root = g.getRandomNode();
        Node found = depthFirstSearch (root, val);
        g.flushFlags();
        return found;
    }
    
    private static Node depthFirstSearch (Node n, int val) {
        if (n.visited())
            return null;
        if (n.getItem() == val)
            return n;
        
        n.visit();
        Node found=null;
        for (Node neighbor : n.getNeighbors().keySet()) {
            found = depthFirstSearch (neighbor, val);
            if (found != null)
                break;
        }
        return found;
    }
        

}
