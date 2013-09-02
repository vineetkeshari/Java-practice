package data.binarytree;

import data.binarytree.Node;
import java.util.Queue;
import java.util.ArrayDeque;

public class BinaryTree {
	private Node root;
	
	public BinaryTree (int[] items) {
		for (int item : items)
			treeInsert (item);
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
	
	public Node getRoot() {
		return root;
	}
	
	public void setRoot (Node node) {
		root = node;
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
			System.out.println(n);
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
		System.out.println (node + " depth: " + depth);
		inOrderPrint (node.getRight(), depth+1);
	}
}
