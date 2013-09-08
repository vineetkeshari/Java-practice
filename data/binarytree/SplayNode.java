package data.binarytree;

public class SplayNode {
    
    private int item;
    private SplayNode left;
    private SplayNode right;
    private SplayNode parent;
    
    public SplayNode (int item) {
        this.item = item;
    }
    
    public int getItem() {
        return item;
    }
    
    public void setItem(int newItem) {
        item = newItem;
    }
    
    public SplayNode getLeft() {
        return left;
    }
    
    public void setLeft(SplayNode node) {
        left = node;
        if (node != null)
            node.setParent(this);
    }
    
    public SplayNode getRight() {
        return right;
    }
    
    public void setRight(SplayNode node) {
        right = node;
        if (node != null)
            node.setParent(this);
    }
    
    public SplayNode getParent() {
        return parent;
    }
    
    public void setParent(SplayNode n) {
        parent = n;
    }
    
    public String toString () {
        return ((getLeft()==null)? "N":getLeft().getItem()) + "/" + item + "\\" + ((getRight()==null)? "N":getRight().getItem());
    }
}
