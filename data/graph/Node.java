package data.graph;

import java.util.HashMap;

public class Node {
    private int item;
    private HashMap<Node,Integer> neighbors = new HashMap<Node,Integer>();
    private boolean visited;
    
    public Node (int num) {
        item = num;
    }
    
    public void visit () {
        visited = true;
    }
    
    public void clearVisited () {
        visited = false;
    }
    
    public boolean visited () {
        return visited;
    }
    
    public int getItem() {
        return item;
    }
    
    public void setItem (int num) {
        item = num;
    }
    
    public void addNeighbor (Node n, int weight) {
        if (!neighbors.containsKey(n))
            neighbors.put(n,weight);
    }
    
    public void removeNeighbor (Node n) {
        if (neighbors.containsKey(n))
            neighbors.remove(n);
    }
    
    public HashMap<Node,Integer> getNeighbors () {
        return (HashMap<Node,Integer>)(neighbors.clone());
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(item + "[");
        for (Node n : neighbors.keySet())
            sb.append(n.getItem()+":"+neighbors.get(n)+",");
        sb.append ("]");
        return new String (sb);
    }

}
