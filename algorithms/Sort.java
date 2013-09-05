package algorithms;

public class Sort {
    public static final int MAXVAL = 100;
    
    public static void main (String[] args) {
        int[] smallArray, mediumArray, largeArray;
        
        System.out.println ("Test merge sort");
        smallArray = generateRandomArray(100);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        mergeSort(smallArray);
        mergeSort(mediumArray);
        mergeSort(largeArray);
        System.out.println ();
        
        System.out.println ("Test insertion sort");
        smallArray = generateRandomArray(100);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        insertionSort(smallArray);
        insertionSort(mediumArray);
        insertionSort(largeArray);
        System.out.println ();
        
        System.out.println ("Test quick sort");
        smallArray = generateRandomArray(100);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        quickSort(smallArray);
        quickSort(mediumArray);
        quickSort(largeArray);
        System.out.println ();
        
        System.out.println ("Test randomized quick sort");
        smallArray = generateRandomArray(100);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        randomizedQuickSort(smallArray);
        randomizedQuickSort(mediumArray);
        randomizedQuickSort(largeArray);
        System.out.println ();
        
        System.out.println ("Test counting sort");
        smallArray = generateRandomArray(100);
        mediumArray = duplicateNTimes (smallArray, 10);
        largeArray = duplicateNTimes (mediumArray, 10);
        countingSort(smallArray);
        countingSort(mediumArray);
        countingSort(largeArray);
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
    
    public static void mergeSort (int[] nums) {
        long start = System.nanoTime( );
        mergeSort (nums, 0, nums.length);
        long end = System.nanoTime( );
        //printArray (nums);
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
    
    public static void insertionSort (int[] nums) {
        long start = System.nanoTime( );
        for (int i=1; i<nums.length; ++i)
            for (int j=i; j>0 && nums[j-1]>nums[j]; --j) {
                int t = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = t;
            }
        long end = System.nanoTime( );
        //printArray (nums);
        System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    public static void quickSort (int[] nums) {
        long start = System.nanoTime( );
        quickSort (nums, 0, nums.length, false);
        long end = System.nanoTime( );
        //printArray (nums);
        System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }
    
    public static void randomizedQuickSort (int[] nums) {
        long start = System.nanoTime( );
        quickSort (nums, 0, nums.length, true);
        long end = System.nanoTime( );
        //printArray (nums);
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
    
    public static void countingSort (int[] nums) {
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
        System.out.println((end-start) + " nanos to sort " + nums.length + " elements.");
    }           
                
}
