package data.binarytree;

public class SplayTree {
    SplayNode root;
    
    public SplayNode getRoot() {
        return root;
    }
    
    public void treeInsert (int[] nums) {
        for (int i : nums)
            treeInsert(i);
    }
    
    public void treeInsert (int num) {
        if (root == null)
            root = new SplayNode(num);
        else
            treeInsert (root, num);
    }
    
    private void treeInsert (SplayNode n, int num) {
        if (num <= n.getItem())
            if (n.getLeft()==null) {
                SplayNode newNode = new SplayNode(num);
                n.setLeft(newNode);
                splay (newNode);
            } else
                treeInsert (n.getLeft(), num);
        else
            if (n.getRight()==null) {
                SplayNode newNode = new SplayNode(num);
                n.setRight(newNode);
                splay (newNode);
            } else
                treeInsert (n.getRight(), num);
    }
    
    public void treeDelete (int num) {
        SplayNode n = treeFind (num);
        if (n == null)
            return;
        else
            if (n.getLeft() == null && n.getRight() == null)
                root = null;
            else
                if (n.getLeft() == null) {
                    root = n.getRight();
                    root.setParent(null);
                } else if (n.getRight() == null) {
                    root = n.getLeft();
                    root.setParent(null);
                } else {
                    root = n.getLeft();
                    root.setParent(null);
                    treeMax(root).setRight(n.getRight());
                }
    }
    
    private SplayNode treeMax (SplayNode n) {
        if (n.getRight() == null)
            return n;
        else
            return treeMax (n.getRight());
    }
    
    public SplayNode treeFind (int num) {
        if (root == null)
            return null;
        else 
            return treeFind (root, num);
    }
    
    private SplayNode treeFind (SplayNode n, int num) {
        if (n.getItem() == num) {
            splay (n);
            return n;
        }
        if (num <= n.getItem())
            if (n.getLeft() == null) {
                splay(n);
                return null;
            } else
                return treeFind (n.getLeft(), num);
        else
            if (n.getRight() == null) {
                splay(n);
                return null;
            } else
                return treeFind (n.getRight(), num);
    }
    
    private void splay (SplayNode n) {
        if (n.getParent() == null) {
            root = n;
            return;
        }
        SplayNode parent = n.getParent(), grand = parent.getParent();
        if (grand == null)
            if (parent.getLeft() == n)
                rotateLeft (n, parent);
            else
                rotateRight (n, parent);
        else
            if (parent.getLeft() == n)
                if (grand.getLeft() == parent)
                    zigZigLeft (n, parent, grand);
                else
                    zigZagLeft (n, parent, grand);
            else
                if (grand.getRight() == parent)
                    zigZigRight (n, parent, grand);
                else
                    zigZagRight (n, parent, grand);
        splay (n);
    }
    
    private void rotateLeft (SplayNode n, SplayNode parent) {
        n.setParent(null);
        parent.setLeft(n.getRight());
        n.setRight(parent);
    }
    
    private void rotateRight (SplayNode n, SplayNode parent) {
        n.setParent(null);
        parent.setRight(n.getLeft());
        n.setLeft(parent);
    }
    
    private void zigZigLeft (SplayNode n, SplayNode parent, SplayNode  grand) {
        SplayNode uber = grand.getParent();
        if (uber != null)
            if (uber.getLeft() == grand)
                uber.setLeft(n);
            else
                uber.setRight(n);
        else
            n.setParent(null);
        parent.setLeft(n.getRight());
        n.setRight(parent);
        grand.setLeft(parent.getRight());
        parent.setRight(grand);
    }
    
    private void zigZigRight (SplayNode n, SplayNode parent, SplayNode  grand) {
        SplayNode uber = grand.getParent();
        if (uber != null)
            if (uber.getLeft() == grand)
                uber.setLeft(n);
            else
                uber.setRight(n);
        else
            n.setParent(null);
        parent.setRight(n.getLeft());
        n.setLeft(parent);
        grand.setRight(parent.getLeft());
        parent.setLeft(grand);
    }
    
    private void zigZagRight (SplayNode n, SplayNode parent, SplayNode  grand) {
        SplayNode uber = grand.getParent();
        if (uber != null)
            if (uber.getLeft() == grand)
                uber.setLeft(n);
            else
                uber.setRight(n);
        else
            n.setParent(null);
        parent.setRight(n.getLeft());
        n.setLeft(parent);
        grand.setLeft(n.getRight());
        n.setRight(grand);
    }
    
    private void zigZagLeft (SplayNode n, SplayNode parent, SplayNode  grand) {
        SplayNode uber = grand.getParent();
        if (uber != null)
            if (uber.getLeft() == grand)
                uber.setLeft(n);
            else
                uber.setRight(n);
        else
            n.setParent(null);
        parent.setLeft(n.getRight());
        n.setRight(parent);
        grand.setRight(n.getLeft());
        n.setLeft(grand);
    }
    
    public String toString() {
        inOrderPrint(root, 0);
        return "";
    }
    
    public void inOrderPrint (SplayNode node, int depth) {
        if (node == null)
            return;
        inOrderPrint (node.getLeft(), depth+1);
        System.out.print (node + "(" + depth + ") ");
        inOrderPrint (node.getRight(), depth+1);
    }
        
        

}
