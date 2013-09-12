package algorithms;

import java.util.HashSet;

public class DynamicProgramming {
    public static void main (String[] args) {
        System.out.println ("Test if k elements of array sum to m");
        kSumToM (new int[]{1,2,3,4,5,6,7,8,9}, 2, 10);
        System.out.println ();
        kSumToM (new int[]{1,2,3,4,5,6,7,8,9}, 3, 10);
        System.out.println ();
        kSumToM (new int[]{1,2,3,4,5,6,7,8,9}, 4, 10);
        System.out.println ();
        kSumToM (new int[]{}, 4, 10);
        System.out.println ();
        kSumToM (new int[]{1,2,3,4,5,6,7,8,9}, 5, 10);
        System.out.println ();
        kSumToM (new int[]{1,2,3,4,5,6,7,8,9}, 2, -1);
        System.out.println ();
        kSumToM (new int[]{1,2,3,4,5,6,7,8,9}, 1, 5);
        System.out.println ();
        
        System.out.println ("Test Longest common subsequence");
        System.out.println (longestCommonSubsequence ("ABCDEFGHIJKLMNOP", "BADCFEHGJILKNMPO"));
        System.out.println (longestCommonSubsequence ("ABCDEFGHIJKLMNOP", "XYZRSTU"));
        System.out.println (longestCommonSubsequence ("ABCDEFGHIJKLMNOP", "XYZGRSTU"));
        System.out.println (longestCommonSubsequence ("", ""));
        System.out.println (longestCommonSubsequence ("", "A"));
    }
    
    public static void kSumToM (int[] nums, int k, int m) {
        HashSet<HashSet<Integer>> allMatches = new HashSet<HashSet<Integer>>();
        HashSet<Integer> runningSet = new HashSet<Integer>();
        kSumToM (allMatches, runningSet, 0, k, m, nums);
        System.out.println ("Found " + allMatches.size() + " matches:");
        for (HashSet<Integer> match : allMatches) {
            for (int i : match)
                System.out.print (i + " ");
            System.out.println();
        }
    }
    
    private static void kSumToM (HashSet<HashSet<Integer>> allMatches, HashSet<Integer> runningSet, int start, int k, int m, int[] nums) {
        if (k==0 && m==0) {
            allMatches.add ((HashSet<Integer>)(runningSet.clone()));
            return;
        }
        if (k==0 || m < 0)
            return;
        if (k > nums.length-start)
            return;
        
        kSumToM (allMatches, runningSet, start+1, k, m, nums);
        if (m >= nums[start]) {
            runningSet.add (start);
            kSumToM (allMatches, runningSet, start+1, k-1, m-nums[start], nums);
            runningSet.remove (start);
        }
        
    }
    
    public static int longestCommonSubsequence (String a, String b) {
        int[][] cache = new int[a.length()][b.length()];
        for (int i=0; i<cache.length; ++i)
            for (int j=0; j<cache[i].length; ++j)
                cache[i][j] = -1;
        return longestCommonSubsequence (a, b, a.length()-1, b.length()-1, cache);
    }
    
    private static int longestCommonSubsequence (String a, String b, int aIndex, int bIndex, int[][] cache) {
        if (aIndex < 0 || bIndex < 0)
            return 0;
        if (cache[aIndex][bIndex] != -1)
            return cache[aIndex][bIndex];
        else {
            int result;
            if (a.charAt(aIndex) == b.charAt(bIndex))
                result = longestCommonSubsequence (a, b, aIndex-1, bIndex-1, cache)+1;
            else {
                int aSideLen = longestCommonSubsequence (a, b, aIndex-1, bIndex, cache);
                int bSideLen = longestCommonSubsequence (a, b, aIndex, bIndex-1, cache);
                if (aSideLen >= bSideLen)
                    result = aSideLen;
                else
                    result = bSideLen;
            }
            cache[aIndex][bIndex] = result;
            return result;
        }
    }
}
