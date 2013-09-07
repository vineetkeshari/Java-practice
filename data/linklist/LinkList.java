package data.linklist;

import data.linklist.Node;

public class LinkList<T> {
    private Node<T> head;
    
    public Node<T> getHead() {
        return head;
    }
    
    public void setHead(Node<T> node) {
        head = node;
    }
    
    public void create(T[] items) {
        if (items.length == 0)
            return;
        head = new Node<T>(items[0]);
        Node<T> current = head;
        for (int i=1; i<items.length; ++i) {
            Node<T> newNode = new Node<T>(items[i]);
            current.setNext (newNode);
            current = newNode;
        }
    }
    
    public void append(T[] items) {
        if (items.length == 0)
            return;
        if (head == null) {
            create(items);
            return;
        }
        Node<T> current;
        for (current = head; current.getNext() != null; current = current.getNext());
        addItems (current, items);
    }
    
    public void append(T item) {
        if (item == null)
            return;
        if (head == null) {
            head = new Node<T>(item);
            return;
        }
        Node<T> current;
        for (current = head; current.getNext() != null; current = current.getNext());
        Node<T> newNode = new Node<T>(item);
        current.setNext(newNode);
    }
    
    protected void addItems(Node<T> node, T[] items) {
        if (items.length == 0)
            return;
        Node<T> current = node;
        for (T item : items) {
            Node<T> newNode = new Node<T>(item);
            current.setNext(newNode);
            current = newNode;
        }
    }
    
    public String toString () {
        System.out.print("[" + ((head==null)? "N" : head.getItem().toString()) + "] ");
        for (Node<T> current = head; current!=null; current=current.getNext()) {
            System.out.print(current + " ");
        }
        return new String();
    }

}
