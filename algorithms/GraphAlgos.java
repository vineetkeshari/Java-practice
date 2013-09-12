package algorithms;

import data.graph.Node;
import data.graph.Graph;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
        g1.addEdge (n9,n1,(int)(Math.random()*10));
        g1.addEdge (n1,n9,(int)(Math.random()*10));
        System.out.println (g1);
        g1.removeEdge (n9,n0);
        System.out.println (g1);
        g1.removeEdge (n0,n9);
        System.out.println (g1);
        g1.removeAnyEdge (n9,n1);
        System.out.println (g1);
        System.out.println ();

        System.out.println("Test depth first search (random root)");
        System.out.println (depthFirstSearch(g1,0));
        System.out.println (depthFirstSearch(g1,1));
        System.out.println (depthFirstSearch(g1,2));
        System.out.println (depthFirstSearch(g1,3));
        System.out.println (depthFirstSearch(g1,4));
        System.out.println (depthFirstSearch(g1,5));
        System.out.println (depthFirstSearch(g1,6));
        System.out.println (depthFirstSearch(g1,7));
        System.out.println (depthFirstSearch(g1,8));
        System.out.println (depthFirstSearch(g1,9));
        System.out.println (depthFirstSearch(g1,10));
        System.out.println ();

        System.out.println("Test breadth first search (random root)");
        System.out.println (breadthFirstSearch(g1,0));
        System.out.println (breadthFirstSearch(g1,1));
        System.out.println (breadthFirstSearch(g1,2));
        System.out.println (breadthFirstSearch(g1,3));
        System.out.println (breadthFirstSearch(g1,4));
        System.out.println (breadthFirstSearch(g1,5));
        System.out.println (breadthFirstSearch(g1,6));
        System.out.println (breadthFirstSearch(g1,7));
        System.out.println (breadthFirstSearch(g1,8));
        System.out.println (breadthFirstSearch(g1,9));
        System.out.println (breadthFirstSearch(g1,10));
        System.out.println ();

        System.out.println("Test Djikstra shortest path (random root)");
        shortestPathsDjikstra(g1);
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
    
    public static Node breadthFirstSearch (Graph g, int val) {
        Queue<Node> q = new ArrayDeque<Node>();
        q.add (g.getRandomNode());
        Node found = null;
        while (!q.isEmpty()) {
            Node n = q.remove();
            if (n.getItem() == val) {
                found = n;
                break;
            }
            n.visit();
            for (Node neighbor : n.getNeighbors().keySet())
                if (!neighbor.visited())
                    q.add(neighbor);
        }
        g.flushFlags();
        return found;
    }
    
    public static void shortestPathsDjikstra (Graph g) {
        Node root = g.getRandomNode();
        System.out.println ("Root: " + root);
    
        ArrayList<NodeAndVal> q = new ArrayList<NodeAndVal>();
        for (Node n : g.getNodes())
            if (n == root)
                q.add (new NodeAndVal(n,0));
            else
                q.add (new NodeAndVal(n,10000));
        
        NodeAndValComparator comp = new NodeAndValComparator();
        ArrayList<NodeAndVal> finalDists = new ArrayList<NodeAndVal>(); 
        
        while (!q.isEmpty()) {
            Collections.sort (q, comp);
            NodeAndVal current = q.remove(q.size()-1);
            finalDists.add (current);
            HashMap<Node,Integer> neighbors = current.n.getNeighbors();
            djikstraRelaxAll (current, neighbors, q);
            
        }
        
        for (NodeAndVal nav : finalDists)
            System.out.print (nav.n + "(" + nav.val + "),");
        System.out.println();
    }
    
    private static class NodeAndVal {
        public Node n;
        public int val;
        
        public NodeAndVal (Node n, int val) {
            this.n = n;
            this.val = val;
        }
    }
    
    private static class NodeAndValComparator implements Comparator<NodeAndVal> {
        public int compare (NodeAndVal a, NodeAndVal b) {
            return (new Integer(b.val)).compareTo(a.val);
        }
    }
    
    private static void djikstraRelaxAll (NodeAndVal current, HashMap<Node,Integer> neighbors, ArrayList<NodeAndVal> q) {
        for (Node neighbor : neighbors.keySet() )
            for (NodeAndVal inQ : q)
                if (inQ.n == neighbor) {
                    int tempDist = current.val + neighbors.get(neighbor);
                    if (tempDist < inQ.val)
                        inQ.val = tempDist;
                    break;
                }
    }

}
