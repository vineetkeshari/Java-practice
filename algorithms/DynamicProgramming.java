package algorithms;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
        System.out.println ();
        
        System.out.println ("Test 0-1 knapsack");
        zeroOneKnapsack (new int[]{1,2,3,4}, 5);
        zeroOneKnapsack (new int[]{1,2,3,4}, 6);
        zeroOneKnapsack (new int[]{1,3,5}, 7);
        zeroOneKnapsack (new int[]{}, 7);
        zeroOneKnapsack (new int[]{1,2,5,10,20,50}, 74);
        zeroOneKnapsack (new int[]{1,2,5,8,10,20,50}, 74);
        zeroOneKnapsack (new int[]{1,2,5,8,10,20,50}, 0);
        System.out.println ();
        
        System.out.println ("Test coins to sum");
        coinsToSum (new int[]{1,2,5,10,20}, 3);
        coinsToSum (new int[]{1,2,5,10,20}, 8);
        coinsToSum (new int[]{1,2,5,10,20}, 13);
        coinsToSum (new int[]{1,2,5,10,20}, 33);
        coinsToSum (new int[]{1,2,5,10,20}, 37);
        coinsToSum (new int[]{1,2,5,10,20}, 101);
        coinsToSum (new int[]{1,2,5,7,10,20}, 3);
        coinsToSum (new int[]{1,2,5,7,10,20}, 8);
        coinsToSum (new int[]{1,2,5,7,10,20}, 13);
        coinsToSum (new int[]{1,2,5,7,10,20}, 33);
        coinsToSum (new int[]{1,2,5,7,10,20}, 37);
        coinsToSum (new int[]{1,2,5,7,10,20}, 101);
        coinsToSum (new int[]{}, 13);
        coinsToSum (new int[]{1,2,3}, 0);
        coinsToSum (new int[]{}, 0);
        System.out.println ();
        
        System.out.println ("Test break into words");
        Set<String> dictionary = new HashSet<String>();
        dictionary.add("bed");
        dictionary.add("bath");
        dictionary.add("and");
        dictionary.add("beyond");
        dictionary.add("bat");
        dictionary.add("hand");
        dictionary.add("plucker");
        dictionary.add("pluckers");
        dictionary.add("wing");
        dictionary.add("win");
        dictionary.add("swing");
        dictionary.add("bar");
        dictionary.add("tab");
        dictionary.add("on");
        dictionary.add("be");
        dictionary.add("at");
        dictionary.add("a");
        breakIntoWords ("bedbathandbeyond", dictionary);
        breakIntoWords ("pluckerswingbar", dictionary);
        breakIntoWords ("atabat", dictionary);
        System.out.println ();
        
        
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
    
    public static void zeroOneKnapsack (int[] weights, int max) {
        System.out.println ("Sum: " + zeroOneKnapsack (weights, max, weights.length-1) + " of max " + max);
    }
    
    private static int zeroOneKnapsack (int[] weights, int max, int index) {
        if (index == -1)
            return 0;
        int withoutMe = zeroOneKnapsack (weights, max, index-1);
        if (max >= weights[index]) {
            int withMe = weights[index] + zeroOneKnapsack (weights, max-weights[index], index-1);
            if (withMe > withoutMe && withMe <= max)
                return withMe;
            else
                return withoutMe;
        } else
            return withoutMe;
    }
    
    public static void coinsToSum (int[] denominations, int sum) {
        HashSet<HashMap<Integer, Integer>> allSolutions = new HashSet<HashMap<Integer, Integer>>();
        HashMap<Integer, Integer> running = new HashMap<Integer, Integer>();
        System.out.println ("Min count : " + coinsToSum (denominations, denominations.length-1, sum, 0, -1, running, allSolutions));
        for (HashMap<Integer, Integer> solution : allSolutions) {
            for (int denomination : solution.keySet())
                System.out.print (denomination + ":" + solution.get(denomination) + ",");
            System.out.println();
        }
        System.out.println();
    }
    
    private static int coinsToSum (int[] denominations, int index, int sum, int count, int minCount,
            HashMap<Integer, Integer> running, HashSet<HashMap<Integer, Integer>> allSolutions) {
        if (sum == 0) {
            if (minCount == -1)
                minCount = count;
            if (count == minCount)
                allSolutions.add((HashMap<Integer,Integer>)(running.clone()));
            if (count < minCount) {
                minCount = count;
                allSolutions.clear();
                allSolutions.add((HashMap<Integer,Integer>)(running.clone()));
            }
            return minCount;
        }
        if (sum < 0 || index < 0)
            return minCount;    
        if (minCount != -1 && count >= minCount)
            return minCount;
        
        minCount = coinsToSum (denominations, index-1, sum, count, minCount, running, allSolutions);
        if (denominations[index] <= sum) {
            int myValue = denominations[index];
            if (running.containsKey(myValue))
                running.put(myValue, running.get(myValue)+1);
            else
                running.put(myValue, 1);
            minCount = coinsToSum(denominations, index-1, sum-myValue, count+1, minCount, running, allSolutions);
            minCount = coinsToSum(denominations, index, sum-myValue, count+1, minCount, running, allSolutions);
            if (running.get(myValue) == 1)
                running.remove(myValue);
            else
                running.put(myValue, running.get(myValue)-1);
        }
        return minCount;
    }
    
    public static void breakIntoWords (String input, Set<String> dictionary) {
        Set<List<String>> allBreakups = new HashSet<List<String>>();
        ArrayList<String> currentSet = new ArrayList<String>();
        breakIntoWords (input, 0, -1, dictionary, currentSet, allBreakups);
        for (List<String> breakup : allBreakups) {
            for (String word : breakup)
                System.out.print(word + " ");
            System.out.println();
        }
    }
    
    private static void breakIntoWords (String input, int index, int last, Set<String> dictionary, ArrayList<String> currentSet, Set<List<String>> allBreakups) {
        if (index == input.length()) {
            if (last == input.length()-1)
                allBreakups.add ((ArrayList<String>)(currentSet.clone()));
            return;
        }
        String word = input.substring(last+1,index+1);
        if (dictionary.contains(word)) {
            currentSet.add (word);
            breakIntoWords (input, index+1, index, dictionary, currentSet, allBreakups);
            currentSet.remove (currentSet.size()-1);
        }
        breakIntoWords (input, index+1, last, dictionary, currentSet, allBreakups);
    }
        
}
