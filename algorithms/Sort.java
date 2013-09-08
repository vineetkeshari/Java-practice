package algorithms;

import java.util.ArrayList;
import algorithms.Math;

public class Sort {
    public static final int MAXVAL = 1000;
    
    public static void main (String[] args) {
        int[] smallArray, mediumArray, largeArray;
        
        System.out.println ("Test merge sort");
        smallArray = generateRandomArray(MAXVAL);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        mergeSort(smallArray, true);
        mergeSort(mediumArray, true);
        mergeSort(largeArray, true);
        System.out.println ();
        
        System.out.println ("Test insertion sort");
        smallArray = generateRandomArray(MAXVAL);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        insertionSort(smallArray, true);
        insertionSort(mediumArray, true);
        insertionSort(largeArray, true);
        System.out.println ();
        
        System.out.println ("Test quick sort");
        smallArray = generateRandomArray(MAXVAL);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        quickSort(smallArray, true);
        quickSort(mediumArray, true);
        quickSort(largeArray, true);
        System.out.println ();
        
        System.out.println ("Test randomized quick sort");
        smallArray = generateRandomArray(MAXVAL);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        randomizedQuickSort(smallArray, true);
        randomizedQuickSort(mediumArray, true);
        randomizedQuickSort(largeArray, true);
        System.out.println ();
        
        System.out.println ("Test counting sort");
        smallArray = generateRandomArray(MAXVAL);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        countingSort(smallArray, true);
        countingSort(mediumArray, true);
        countingSort(largeArray, true);
        System.out.println ();
        
        System.out.println ("Test bucket sort");
        smallArray = generateRandomArray(MAXVAL);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        bucketSort(smallArray, true);
        bucketSort(mediumArray, true);
        bucketSort(largeArray, true);
        System.out.println ();
    }
    
    private static int[] generateRandomArray (int size) {
        int[] array = new int[size];
        for (int i=0; i<size; ++i)
            array[i] = (int)(Math.random()*MAXVAL);
        return array;
    }
    
    private static int[] duplicateNTimes (int[] array, int n) {
        int[] newArray = new int[array.length*n];
        for (int i=0; i<n; ++i)
            for (int j=0; j<array.length; ++j)
                newArray[i*array.length+j] = array[j];
        return newArray;
    }
    
    private static void printArray (int[] nums) {
        for (int i : nums)
            System.out.print (i + " ");
        System.out.println();
    }
    
    public static void mergeSort (int[] nums, boolean showTiming) {
        long start = System.nanoTime( );
        mergeSort (nums, 0, nums.length);
        long end = System.nanoTime( );
        //printArray (nums);
        if (showTiming)
            System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    private static void mergeSort (int[] nums, int start, int end) {
        if (end-start < 2)
            return;
        int mid = (start + end) / 2;
        mergeSort (nums, start, mid);
        mergeSort (nums, mid, end);
        int[] temp = new int[end-start];
        int p1 = start, p2 = mid, counter=0;
        while (p1 < mid && p2 < end)
            if (nums[p1] < nums[p2]) {
                temp[counter++] = nums[p1];
                ++p1;
            } else {
                temp[counter++] = nums[p2];
                ++p2;
            }
        if (p1<mid)
            for (; p1<mid; ++p1)
                temp[counter++] = nums[p1];
        if (p2<end)
            for(; p2<end; ++p2)
                temp[counter++] = nums[p2];
        for (int i=0; i<temp.length; ++i)
            nums[start+i] = temp[i];
    }
    
    public static void insertionSort (int[] nums, boolean showTiming) {
        long start = System.nanoTime( );
        for (int i=1; i<nums.length; ++i)
            for (int j=i; j>0 && nums[j-1]>nums[j]; --j) {
                int t = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = t;
            }
        long end = System.nanoTime( );
        //printArray (nums);
        if (showTiming)
            System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    public static void quickSort (int[] nums, boolean showTiming) {
        long start = System.nanoTime( );
        quickSort (nums, 0, nums.length, false);
        long end = System.nanoTime( );
        //printArray (nums);
        if (showTiming)
            System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    public static void randomizedQuickSort (int[] nums, boolean showTiming) {
        long start = System.nanoTime( );
        quickSort (nums, 0, nums.length, true);
        long end = System.nanoTime( );
        //printArray (nums);
        if (showTiming)
            System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    private static void quickSort (int[] nums, int start, int end, boolean randomize) {
        if (end-start < 2)
            return;
        int pivot = partition (nums, start, end, randomize);
        quickSort (nums, start, pivot, randomize);
        quickSort (nums, pivot+1, end, randomize);
    }
    
    private static int partition (int[] nums, int start, int end, boolean randomize) {
        int t;
        if (randomize) {
            int randPivot = start + (int)(Math.random()*(end-start));
            t = nums[end-1];
            nums[end-1] = nums[randPivot];
            nums[randPivot] = t;
        }

        int s=start;
        for (int i=start; i<end-1; ++i)
            if (nums[i] < nums[end-1]) {
                t = nums[s];
                nums[s++] = nums[i];
                nums[i] = t;
            }
        t = nums[s];
        nums[s] = nums[end-1];
        nums[end-1] = t;
        return s;
    }
    
    public static void countingSort (int[] nums, boolean showTiming) {
        long start = System.nanoTime( );
        int[] counts = new int[MAXVAL];
        for (int num : nums) {
            counts[num]++;
        }
        int index=0;
        for (int i=0; i<counts.length; ++i)
            for (int j=0; j<counts[i]; ++j)
                nums[index++] = i;
        long end = System.nanoTime( );
        //printArray(nums);
        if (showTiming)
            System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    public static void bucketSort (int[] nums, boolean showTiming) {
        long start = System.nanoTime( );
        final int BUCKETSIZE = (int)(Math.sqrt(MAXVAL));
        Bucket[] buckets = new Bucket[MAXVAL/BUCKETSIZE+1];
        for (int i=0; i<buckets.length; ++i)
            buckets[i] = new Bucket();
        for (int i : nums) {
            int bucket = i/BUCKETSIZE;
            buckets[bucket].numbersList.add(i);
        }
        int base=0;
        for (int i=0; i<buckets.length; ++i) {
            arrayListToArray(buckets[i]);
            quickSort (buckets[i].numbers, false);
            addAll (nums, buckets[i].numbers, base);
            base += buckets[i].numbers.length;
        }
        long end = System.nanoTime( );
        //printArray(nums);
        if (showTiming)
            System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    private static class Bucket {
        public ArrayList<Integer> numbersList = new ArrayList<Integer>();
        public int[] numbers;
    }
    
    private static void arrayListToArray (Bucket b) {
        b.numbers = new int[b.numbersList.size()];
        for (int i=0; i<b.numbers.length; ++i)
            b.numbers[i] = b.numbersList.get(i);
        b.numbersList = null;
    }
    
    private static void addAll (int[] nums, int[] bucket, int base) {
        for (int i=0; i<bucket.length; ++i)
            nums[base+i] = bucket[i];
    }
        
}
