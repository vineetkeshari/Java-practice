package data.linklist;

public class Node<T>{
	private T item;
	private Node<T> next;
	
	public Node (T newItem) {
		item = newItem;
	}
	
	public T getItem () {
		return item;
	}
	
	public void setItem (T newItem) {
		item = newItem;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public void setNext(Node<T> node) {
		next = node;
	}
	
	public String toString() {
		return "(" + item.toString() + "," + ((next==null)? "N":next.getItem().toString()) + ")";
	}

}
