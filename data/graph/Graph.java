package data.graph;

import java.util.HashSet;
import java.util.Iterator;

public class Graph {
    private HashSet<Node> nodes = new HashSet<Node>();
    
    public void addNodes (Node[] ns) {
        for (Node n : ns)
            addNode (n);
    }
    
    public void addNode (Node n) {
        if (!nodes.contains(n))
            nodes.add (n);
    }
    
    public void removeNode (Node n) {
        if (nodes.contains(n))
            nodes.remove(n);
    }
    
    public void addEdge (Node n1, Node n2, int weight) {
        if (nodes.contains(n1) && nodes.contains(n2))
            n1.addNeighbor(n2,weight);
    }
    
    public void removeEdge (Node n1, Node n2) {
        if (nodes.contains(n1) && nodes.contains(n2))
            n1.removeNeighbor(n2);
    }
    
    public void addUnEdge (Node n1, Node n2, int weight) {
        if (nodes.contains(n1) && nodes.contains(n2)) {
            n1.addNeighbor(n2, weight);
            n2.addNeighbor(n1, weight);
        }
    }
    
    public void removeAnyEdge (Node n1, Node n2) {
        if (nodes.contains(n1) && nodes.contains(n2)) {
            n1.removeNeighbor(n2);
            n2.removeNeighbor(n1);
        }
    }
    
    public Node getRandomNode () {
        Iterator<Node> i = nodes.iterator();
        if (i.hasNext())
            return i.next();
        else
            return null;
    }
    
    public String toString () {
        for (Node n : nodes)
            System.out.print(n + ",\t");
        System.out.println();
        return "";
    }

}
