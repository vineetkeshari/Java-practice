package algorithms;

import data.linklist.LinkList;
import data.linklist.Node;
import data.linklist.DoublyLinkedList;
import data.linklist.BidirectionalNode;

public class LinkListAlgos {
    public static void main (String[] args) {
        System.out.println("Test LinkList append and reverse");
        LinkList<Integer> l1 = new LinkList<Integer> ();
        l1.create (new Integer[]{1,2,3,4,5});
        System.out.println(l1);
        reverse(l1);
        System.out.println(l1);
        reverse(l1);
        System.out.println(l1);
        l1.append(new Integer[]{6,7,8,9,10});
        System.out.println(l1);
        reverse(l1);
        System.out.println(l1);
        reverse(l1);
        System.out.println(l1);

        System.out.println("\nTest DoublyLinkList append and reverse");
        DoublyLinkedList<Integer> l2 = new DoublyLinkedList<Integer>();
        l2.create (new Integer[]{1,2,3,4,5});
        System.out.println(l2);
        reverse(l2);
        System.out.println(l2);
        reverse(l2);
        System.out.println(l2);
        l2.append(new Integer[]{6,7,8,9,10});
        System.out.println(l2);
        reverse(l2);
        System.out.println(l2);
        reverse(l2);
        System.out.println(l2);

        System.out.println("\nTest LinkList mergeSorted");
        LinkList<Integer> l3 = new LinkList<Integer> ();
        l3.create (new Integer[]{1,7,8,11,13});
        LinkList<Integer> l4 = new LinkList<Integer> ();
        l4.create (new Integer[]{2,3,7,8,9,14});
        LinkList<Integer> l5 = mergeSortedLists (l3, l4);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println(l5);

        System.out.println("\nTest LinkList reverse sets of three");
        LinkList<Integer> l6 = new LinkList<Integer> ();
        l6.create (new Integer[]{1,7,8,11,13,17,19,22,28,39,41,42});
        System.out.println(l6);
        reverseSetsOfThree (l6);
        System.out.println(l6);
        LinkList<Integer> l7 = new LinkList<Integer> ();
        l7.create (new Integer[]{1,7,8,11,13,17,19,22,28,39,41});
        System.out.println(l7);
        reverseSetsOfThree (l7);
        System.out.println(l7);
        LinkList<Integer> l8 = new LinkList<Integer> ();
        l8.create (new Integer[]{1,7,8,11,13,17,19,22,28,39});
        System.out.println(l8);
        reverseSetsOfThree (l8);
        System.out.println(l8);
        
    }
    
    public static <T> void reverse (LinkList<T> linklist) {
        if (linklist.getHead() == null)
            return;
        Node<T> current = linklist.getHead(), last = null;
        while (current.getNext() != null) {
            Node<T> temp = current.getNext();
            current.setNext(last);
            last = current;
            current = temp;
        }
        current.setNext(last);
        linklist.setHead(current);
    }
    
    public static <T> void reverse (DoublyLinkedList<T> linklist) {
        if (linklist.getHead() == null)
            return;
        BidirectionalNode<T> current = linklist.getHead();
        while (current != null) {
            BidirectionalNode<T> temp = current.getNext();
            current.setNext(current.getPrevious());
            current.setPrevious(temp);
            current = temp;
        }
        BidirectionalNode<T> temp = linklist.getHead();
        linklist.setHead(linklist.getTail());
        linklist.setTail(temp);
    }
    
    public static <T> LinkList<T> mergeSortedLists (LinkList<T> list1, LinkList<T> list2) {
        if (list1.getHead() == null)
            return list2;
        if (list2.getHead() == null)
            return list1;
        if (!(list1.getHead().getItem() instanceof Integer)) {
            System.out.println("Method not yet implemented for non-integer values.");
            return null;
        }
        LinkList<T> newList = new LinkList<T> ();
        Node<T> node1 = list1.getHead(), node2 = list2.getHead();
        while (node1 != null && node2 != null) {
            if (((Integer)node1.getItem()).intValue() < ((Integer)node2.getItem()).intValue()) {
                newList.append(node1.getItem());
                node1 = node1.getNext();
            } else {
                newList.append(node2.getItem());
                node2 = node2.getNext();
            }
        }
        if (node1 == null) {
            while (node2 != null) {
                newList.append(node2.getItem());
                node2 = node2.getNext();
            }
        }
        if (node2 == null) {
            while (node1 != null) {
                newList.append(node1.getItem());
                node1 = node1.getNext();
            }
        }
        
        return newList;
    }
    
    public static <T> void reverseSetsOfThree (LinkList<T> linklist) {
        if (linklist.getHead() == null)
            return;
        Node<T> current = linklist.getHead(), last = null, previous = null, lastToLast = null;
        boolean setHead = false;
        int counter = 0;
        while (current != null) {
            Node<T> temp = current.getNext();
            if (counter == 0) {
                lastToLast = last;
                last = current;
                current.setNext(null);
            } else if (counter == 2) {
                current.setNext(previous);
            } else {
                current.setNext(previous);
            }
            if (counter == 2 || temp == null) {
                if (lastToLast != null)
                    lastToLast.setNext(current);
                if (!setHead) {
                    linklist.setHead(current);
                    setHead = true;
                }
            }
            previous = current;
            current = temp;
            if (counter++ == 2)
                counter = 0;
        }
    }

}
