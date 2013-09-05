package data.binarytree;

import data.binarytree.Node;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class BinaryTree {
	private Node root;
	
	public BinaryTree (int[] items) {
		for (int item : items)
			treeInsert (item);
	}
    
    public Node getRoot() {
        return root;
    }
    
    public void setRoot (Node node) {
        root = node;
    }
	
	public void treeInsert (int[] items) {
		for (int item : items)
			treeInsert (item);
	}
	
	public void treeInsert (int item) {
		if (root == null)
			root = new Node (item);
		else
			insert (root, item);
	}
	
	private void insert (Node node, int item) {
		if (item <= node.getItem())
			if (node.getLeft() == null)
				node.setLeft (new Node(item));
			else
				insert (node.getLeft(), item);
		else
			if (node.getRight() == null)
				node.setRight (new Node(item));
			else
				insert (node.getRight(), item);
	}
	
	public Node treeFind (int item) {
	    ChildAndParent cap = new ChildAndParent();
	    treeFind (root, null, item, cap);
	    return cap.n;
	}
	
	private class ChildAndParent {
	    public Node n;
	    public Node parent;
	}
	
	private void treeFind (Node n, Node p, int item, ChildAndParent cap) {
	    if (n == null || cap.n != null && cap.parent != null)
	        return;
	    else if (item == n.getItem()) {
	        cap.n = n;
	        cap.parent = p;
	    } else if (item < n.getItem())
	        treeFind (n.getLeft(), n, item, cap);
	    else
	        treeFind (n.getRight(), n, item, cap);
	}
	
	public Node treeSuccessor (int item) {
	    ChildAndParent cap = new ChildAndParent();
	    treeFind (root, null, item, cap);
	    if (cap.n == null)
	        return null;
	    return treeSuccessor (cap.n, cap.parent);
	}
	
	private Node treeSuccessor (Node n, Node p) {
	    if (n.getRight() == null)
	        if (p == null || p.getRight() == n)
	            return null;
	        else
	            return p;
	    else
	        return treeMin (n.getRight());
	}
	
	private Node treeMin (Node n) {
	    if (n.getLeft() == null)
	        return n;
	    else
	        return treeMin (n.getLeft());
	}
    
    public Node treePredecessor (int item) {
        ChildAndParent cap = new ChildAndParent();
        treeFind (root, null, item, cap);
        if (cap.n == null)
            return null;
        return treePredecessor (cap.n, cap.parent);
    }
    
    private Node treePredecessor (Node n, Node p) {
        if (n.getLeft() == null)
            if (p == null || p.getLeft() == n)
                return null;
            else
                return p;
        else
            return treeMax (n.getLeft());
    }
    
    private Node treeMax (Node n) {
        if (n.getRight() == null)
            return n;
        else
            return treeMax (n.getRight());
    }
    
	public Node getParent (Node n) {
		Node t = root;
		if (t == n)
			return null;
		return findChildOf (t, n);
	}
	
	private Node findChildOf (Node parent, Node n) {
		if (parent == null)
			return null;
		if (parent.getLeft() == n || parent.getRight() == n)
			return parent;
		if (n.getItem() <= parent.getItem())
			return findChildOf (parent.getLeft(), n);
		else
			return findChildOf (parent.getRight(), n);
	}
	
	public void breadthFirst () {
		Queue<Node> q = new ArrayDeque<Node>();
		q.add (root);
		while (!q.isEmpty()) {
			Node n = q.remove();
			System.out.print(n + " ");
			if (n.getLeft() != null)
				q.add (n.getLeft());
			if (n.getRight() != null)
				q.add (n.getRight());
		}
	}
	
	public String toString() {
		inOrderPrint(root, 0);
		return "";
	}
	
	public void inOrderPrint (Node node, int depth) {
		if (node == null)
			return;
		inOrderPrint (node.getLeft(), depth+1);
		System.out.print (node + "(" + depth + ") ");
		inOrderPrint (node.getRight(), depth+1);
	}
	
	public String serialize () {
	    StringBuffer sb = new StringBuffer();
	    serialize(root, sb);
	    return new String(sb);
	}
	
	private void serialize (Node n, StringBuffer sb) {
	    if (n == null) {
	        sb.append("N ");
	        return;
	    }
	    sb.append(n.getItem() + " ");
	    serialize (n.getLeft(), sb);
	    serialize (n.getRight(), sb);
	}
	
	public static BinaryTree deserialize (String s) {
	    if (s.equals("N ") || s.equals(" ") || s.equals(""))
	        return null;
	    ArrayList<String> nodes = new ArrayList<String>();
	    for (String val : s.split(" "))
	        nodes.add (val);
	    BinaryTree b = new BinaryTree(new int[]{});
	    b.setRoot (deserialize (nodes.iterator()));
	    return b;
	}
	
	private static Node deserialize (Iterator<String> nodes) {
	    if (!nodes.hasNext())
	        return null;
	    String newVal = nodes.next();
	    if (newVal.equals("N"))
	        return null;
	    Node n = new Node (Integer.parseInt(newVal));
	    n.setLeft (deserialize(nodes));
	    n.setRight (deserialize(nodes));
	    return n;
	}
	        
	    
}
