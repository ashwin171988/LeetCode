package practice.dynamic.geeks;

import java.util.HashMap;

public class DynamicMain {

	public static void main(String[] args) {
		DynamicProblems d = new DynamicProblems();

		// Nth Ugly nunmber
		System.out.println(d.getNthUglyNumber(10));
		System.out.println(d.getNthUglyNumber(2));

		// coin change
		System.out.println("Coin change");
		System.out.println(d.getCoinChange(new int[] { 1, 2, 3 }, 5));

		System.out.println(d.getCoinChangeV2(new int[] { 1, 2, 3 }, 15, new HashMap<Integer, Integer>()));
		System.out.println(d.coinChangeTotal(new int[] { 1, 2, 3 }, 5, new int[8]));

		// friend connections
		System.out.println(d.getFriendPairs(new int[] { 1, 2, 3 }));

		// subset Sum
		System.out.println(d.findSubsetSum(new int[] { 3, 34, 4, 12, 5, 2 }, 12));

		// rod cutting problem no duplicates
		System.out.println(d.rodCutting(new int[] { 1, 5, 8, 9, 10, 17, 17 }));

		// longest common subsequence
		System.out.println(d.longestCommonSubsequence("ABCDGH", "ACDDFHR"));

		// longest common subsequence where the common subsequences do not have a common
		// index between them
		System.out.println(d.longestCommonSubsequenceWithoutCommonIndex("AABEBCDD"));

		// longestIncreasingSubsequence
		System.out.println(d.longestIncreasingSubsequence(new int[] { 10, 22, 9, 33, 21, 50, 41, 60, 80 }));
		
		//maximum sum increasing subsequence
		System.out.println(d.maximumSumIncreasingSubsequence(new int[]{1, 101, 2, 3, 100, 4, 5}));

	}
}
