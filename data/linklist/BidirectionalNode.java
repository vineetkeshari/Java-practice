package data.linklist;

public class BidirectionalNode<T> {
    private BidirectionalNode<T> previous, next;
    private T item;
    
    public BidirectionalNode (T item) {
        this.item = item;
    }
    
    public BidirectionalNode<T> getPrevious() {
        return previous;
    }
    
    public void setPrevious (BidirectionalNode<T> node) {
        previous = node;
    }
    
    public BidirectionalNode<T> getNext() {
        return next;
    }
    
    public void setNext (BidirectionalNode<T> node) {
        next = node;
    }
    
    public T getItem () {
        return item;
    }
    
    public void setItem (T newItem) {
        item = newItem;
    }
    
    public String toString() {
        return "(" + ((previous==null)? "N":previous.getItem().toString()) + "," + item.toString() +
                "," + ((next==null)? "N":next.getItem().toString()) + ")";
    }

}
