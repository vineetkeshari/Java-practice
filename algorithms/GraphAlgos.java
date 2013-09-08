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
        
        Graph g1 = new Graph();
        g1.addNodes (new Node[]{n0,n1,n2,n3,n4,n5,n6,n7,n8,n9});
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
        
        System.out.println (g1);
    }
    
    public static Node depthFirstSearch (Graph g, int val) {
        Node root = g.getRandomNode();
        return depthFirstSearch (root, val);
    }
    
    public static Node depthFirstSearch (Node n, int val) {
        if (n.getItem() == val)
            return n;
        if (n.getItem() == -1)
            return null;
        
        int temp = n.getItem();
        n.setItem (-1);
        for (Node neighbor : n.getNeighbors().keySet()) {
            Node found = depthFirstSearch (neighbor, val);
            if (found != null)
                return found;
        }
        n.setItem (temp);
        return null;
    }
        

}
