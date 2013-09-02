package data.binarytree;

public class Node {
	private int item;
	private Node left;
	private Node right;
	
	public Node (int item) {
		this.item = item;
	}
	
	public int getItem() {
		return item;
	}
	
	public void setItem(int newItem) {
		item = newItem;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node node) {
		left = node;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node node) {
		right = node;
	}
	
	public String toString() {
		return ((getLeft()==null)? "N":getLeft().getItem()) + "/" + item + "\\" + ((getRight()==null)? "N":getRight().getItem());
	}

}
