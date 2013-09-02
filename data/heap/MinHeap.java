package data.heap;

import java.util.Arrays;

public class MinHeap {
    public static void main (String[] args) {
        MinHeap h1 = new MinHeap(new int[]{1,15,8,41,9,13,6,0,14,7,31,9,2,6,71,4,5,8,31,7});
        System.out.println(h1);
        h1.addKeys(new int[]{5,-8,13,8,56,8,-3,14,91,5,-1,-21,4});
        System.out.println(h1);
    }
    
    protected int[] keys;
    
    public MinHeap (int[] keys) {
        buildHeap (keys);
    }
    
    private void buildHeap (int[] vals) {
        keys = new int[vals.length+1];
        for (int i=0; i<vals.length; ++i)
            keys[i+1] = vals[i];
        for (int i=keys.length/2; i>0; --i)
            bubbleDown(i);
    }
    
    private int parent (int i) {
        return i/2;
    }
    
    private int left (int i) {
        return 2*i;
    }
    
    private int right (int i) {
        return 2*i + 1;
    }
    
    protected void bubbleDown (int i) {
        if (i > keys.length/2)
            return;
        if (left(i) < keys.length && keys[left(i)] < keys[i] && right(i) < keys.length && keys[left(i)] <= keys[right(i)]) {
            int temp = keys[i];
            keys[i] = keys[left(i)];
            keys[left(i)] = temp;
            bubbleDown(left(i));
        } else if (right(i) < keys.length && keys[right(i)] < keys[i] && right(i) < keys.length && keys[right(i)] <= keys[left(i)]) {
            int temp = keys[i];
            keys[i] = keys[right(i)];
            keys[right(i)] = temp;
            bubbleDown(right(i));
        }
    }
    
    public void addKeys (int[] vals) {
        for (int i : vals)
            if (i >= 0)
                addKey(i);
    }
    
    public void addKey (int i) {
        keys = Arrays.copyOf(keys, keys.length+1);
        keys[keys.length-1] = i;
        minHeapify(keys.length-1);
    }
    
    private void minHeapify (int i) {
        if (i==1)
            return;
        if (keys[i] < keys[parent(i)]) {
            int temp = keys[i];
            keys[i] = keys[parent(i)];
            keys[parent(i)] = temp;
            minHeapify(parent(i));
        }
    }
    
    public String toString () {
        for (int i=1; i<keys.length; ++i)
            System.out.print (i + ": " + keys[i] + "\t");
        return "\n";
    }
    
}
