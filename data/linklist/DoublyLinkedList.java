package data.linklist;

import data.linklist.BidirectionalNode;

public class DoublyLinkedList<T> {
    private BidirectionalNode<T> head, tail;
    
    public BidirectionalNode<T> getHead () {
        return head;
    }
    
    public void setHead (BidirectionalNode<T> node) {
        head = node;
    }
    
    public BidirectionalNode<T> getTail () {
        return tail;
    }
    
    public void setTail (BidirectionalNode<T> node) {
        tail = node;
    }
    
    public void create(T[] items) {
        if (items.length == 0)
            return;
        head = new BidirectionalNode<T>(items[0]);
        BidirectionalNode<T> current = head, previous = null;
        for (int i=1; i<items.length; ++i) {
            BidirectionalNode<T> newNode = new BidirectionalNode<T>(items[i]);
            current.setNext (newNode);
            current.setPrevious (previous);
            previous = current;
            current = newNode;
        }
        tail = current;
        current.setPrevious(previous);
    }
    
    public void append(T[] items) {
        if (items.length == 0)
            return;
        if (head == null) {
            create (items);
            return;
        }
        BidirectionalNode<T> current = tail;
        tail = addItems (current, items);
    }
    
    protected BidirectionalNode<T> addItems(BidirectionalNode<T> node, T[] items) {
        if (items.length == 0)
            return node;
        BidirectionalNode<T> current = node, previous = node.getPrevious();
        for (T item : items) {
            BidirectionalNode<T> newNode = new BidirectionalNode<T>(item);
            current.setNext(newNode);
            current.setPrevious(previous);
            previous = current;
            current = newNode;
        }
        current.setPrevious(previous);
        return current;
    }
    
    public String toString () {
        System.out.print("[" + ((head==null)? "N" : head.getItem().toString()) + "] ");
        for (BidirectionalNode<T> current = head; current!=null; current=current.getNext()) {
            System.out.print(current + " ");
        }
        System.out.print("[" + ((tail==null)? "N" : tail.getItem().toString()) + "]");
        return new String();
    }

}
