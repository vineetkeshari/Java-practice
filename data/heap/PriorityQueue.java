package data.heap;

import data.heap.MinHeap;
import java.util.Arrays;

public class PriorityQueue extends MinHeap {
    public static void main (String[] args) {
        PriorityQueue h1 = new PriorityQueue(new int[]{1,15,8,41,9,13,6,0,14,7,31,9,2,6,71,4,5,8,31,7});
        System.out.println(h1);
        h1.addKeys(new int[]{5,-8,13,8,56,8,-3,14,91,5,-1,-21,4});
        System.out.println(h1);
        System.out.println (h1.extractMin());
        System.out.println(h1);
        System.out.println (h1.extractMin());
        System.out.println(h1);
        System.out.println (h1.extractMin());
        System.out.println(h1);
        System.out.println (h1.extractMin());
        System.out.println(h1);
        System.out.println (h1.extractMin());
        System.out.println(h1);
    }
    
    public PriorityQueue (int[] vals) {
        super(vals);
    }
    
    public int extractMin() {
        if (keys == null || keys.length==1)
            return -1;
        int temp = keys[1];
        keys[1] = keys[keys.length-1];
        keys = Arrays.copyOf (keys, keys.length-1);
        bubbleDown(1);
        return temp;
    }

}
