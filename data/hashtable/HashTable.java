package data.hashtable;

import algorithms.Math;

public class HashTable {
    public static void main (String[] args) {
        String[] lotsOfVals = new String[]{"hello", "my", "name", "is", "this", "i", "am", "a", "class",
                "a", "class", "is", "a", "data", "type", "this", "is", "true", "promise", "you", "have",
                "my", "word", "i", "am", "sure", "this", "is", "my", "name", "this", "is", "true"};
        HashTable h1 = new HashTable(lotsOfVals);
        System.out.println (h1);
        System.out.println ();
        h1.add (new String[]{"what", "is", "my", "name", "who", "am", "i", "i", "am", "a", "class"});
        System.out.println (h1);
    }
    
    private Node[] keys;
    private class Node {
        public String value;
        public int count;
        public Node next;
        public Node (String s) {
            value = s;
            count = 1;
        }
    }
    
    public HashTable (String[] values) {
        keys = new Node[nextPrime((int)(values.length))];
        add (values);
    }
    
    public void add (String[] values) {
        for (String value : values)
            add (value);
    }
    
    public void add (String value) {
        int index = getHash(value);
        if (keys[index] == null) {
            keys[index] = new Node(value);
            return;
        }
        Node c = keys[index];
        while (c.next != null) {
            if (c.value.equals(value)) {
                ++c.count;
                return;
            }
            c = c.next;
        }
        if (c.value.equals(value)) {
            ++c.count;
            return;
        }
        c.next = new Node(value);
    }
    
    public int find (String value) {
        int index = getHash(value);
        if (keys[index] == null)
            return 0;
        Node c = keys[index];
        while (c != null)
            if (c.value.equals(value))
                return c.count;
        return 0;
    }
    
    private int nextPrime (int n) {
        return Math.nextPrime(n);
    }
    
    private int getHash (String key) {
        int sum = 0;
        for (int i=0; i<key.length(); ++i)
            sum += (int)(key.charAt(i));
        return ((sum * key.length()) % keys.length);
    }
    
    public String toString () {
        for (int i=0; i < keys.length; ++i) {
            System.out.print (i + "\t");
            int chainLength = 0;
            StringBuffer allValues = new StringBuffer();
            Node n = keys[i];
            while (n != null) {
                allValues.append ("\t" + n.count + " : " + n.value);
                n = n.next;
                ++chainLength;
            }
            System.out.print (chainLength + new String(allValues) + "\n");
        }
        return "";
    }
    
}
