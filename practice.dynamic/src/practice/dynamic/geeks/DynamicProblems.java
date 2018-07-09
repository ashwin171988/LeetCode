package practice.dynamic.geeks;

import java.util.HashMap;

public class DynamicProblems {

	public int getNthUglyNumber(int n) {
		if (n <= 0)
			return -1;
		int[] result = new int[n + 1];

		// start with 1
		result[0] = 1;

		// any no should be divisible by 2, 3 5
		int twoIndex = 0;
		int threeIndex = 0;
		int fiveIndex = 0;

		int multipleOfTwo = 2, multipleOfThree = 3, multipleOfFive = 5;
		for (int i = 1; i < 150; i++) {
			if (i > n)
				break;

			int twoResult = result[twoIndex] * multipleOfTwo;
			int threeResult = result[threeIndex] * multipleOfThree;
			int fiveResult = result[fiveIndex] * multipleOfFive;

			int minResult = Math.min(twoResult, Math.min(threeResult, fiveResult));
			result[i] = minResult;

			if (minResult == twoResult)
				twoIndex++;
			if (minResult == threeResult)
				threeIndex++;
			if (minResult == fiveResult)
				fiveIndex++;
		}

		// for (int i = 0; i <= n; i++) {
		// System.out.print(i + " " + result[i]);
		// System.out.println();
		// }

		return result[n - 1];
	}

	int coinChange(int[] coins, int n) {
		if (coins == null || coins.length == 0)
			return 0;

		return getCoinChange(coins, n);
	}

	int getCoinChange(int[] coins, int n) {
		int[] result = new int[n + 1];

		result[0] = 1;
		for (int i = 0; i < coins.length; i++) {
			for (int j = 1; j <= n; j++) {
				if (j - coins[i] < 0)
					continue;

				result[j] = result[j] + result[j - coins[i]];
			}
		}

		return result[n];
	}

	public int coinChangeTotal(int[] coins, int sum, int[] valueArray) {
		if (sum == 0)
			return 0;

		if (valueArray[sum] > 0)
			return 0;

		int result = 0;
		for (int i = 0; i < coins.length; i++) {
			if (sum - coins[i] < 0)
				continue;

			result += coinChangeTotal(coins, sum - coins[i], valueArray);
		}

		valueArray[sum] = 1 + result;

		return valueArray[sum];
	}

	// gets the minimum no of coins i can use to get the total

	int getCoinChangeV2(int[] coins, int n, HashMap<Integer, Integer> map) {
		if (n == 0)
			return 0;

		if (map.containsKey(n))
			return map.get(n);

		int result = 0;
		for (int i = 0; i < coins.length; i++) {
			if (n >= coins[i])
				result = 1 + getCoinChangeV2(coins, n - coins[i], map);
		}

		if (map.containsKey(n)) {
			map.put(n, map.get(n) + result);
		} else {
			map.put(n, result);
		}
		return result;
	}

	int getPairs(int n) {
		int[] result = new int[n + 1];

		for (int i = 0; i <= 2; i++)
			result[i] = i;

		for (int i = 3; i <= n; i++) {
			result[i] = result[i - 1] + (i - 1 * result[i - 2]);
		}

		return result[n];

	}

	public int getFriendPairs(int[] is) {

		return getPairs(is.length);
	}

	boolean findSubsetSum(int[] input, int sum) {
		if (input == null || input.length == 0)
			return false;

		boolean[][] result = new boolean[input.length + 1][sum + 1];

		for (int row = 0; row < result.length; row++) {
			result[row][0] = true;
		}

		for (int row = 1; row < result.length; row++) {
			for (int col = 1; col <= sum; col++) {
				if (input[row - 1] > col)
					result[row][col] = false;

				if (input[row - 1] > col) {
					result[row][col] = false;
					continue;
				}

				result[row][col] = result[row - 1][col] || result[row - 1][col - input[row - 1]];
			}
		}

		boolean finalResult = false;
		for (int row = 0; row < result.length; row++) {
			finalResult = finalResult || result[row][sum];
		}

		return finalResult;

	}

	public int rodCutting(int[] is) {

		if (is == null || is.length == 0)
			return 0;

		int[] result = new int[is.length + 1];
		result[0] = is[0];

		for (int length = 2; length <= is.length; length++) {
			int maxValue = is[length - 1];

			for (int rodLength = 0; rodLength < length; rodLength++) {
				int newValue = result[rodLength] + result[length - rodLength];

				maxValue = Math.max(maxValue, newValue);
			}
			result[length] = maxValue;
		}
		return result[is.length];

	}

	public int longestCommonSubsequence(String s1, String s2) {

		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
			return 0;
		int[][] result = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					result[i][j] = 1 + result[i - 1][j - 1];
				} else
					result[i][j] = Math.max(result[i][j - 1], result[i - 1][j]);
			}
		}
		System.out.println("Longest coommon subsequence ");
		printLongestCommonSubsequence(result, s1, s2);

		return result[result.length - 1][result[0].length - 1];
	}

	public int longestCommonSubsequenceWithoutCommonIndex(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int[][] result = new int[s.length() + 1][s.length() + 1];

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= s.length(); j++) {
				if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
					result[i][j] = 1 + result[i - 1][j - 1];
				} else {
					result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
				}
			}
		}

		System.out.println("Longest coommon subsequence ");
		printLongestCommonSubsequence(result, s, s);
		return result[result.length - 1][result[0].length - 1];

	}

	public void printLongestCommonSubsequence(int[][] matrix, String s1, String s2) {

		StringBuilder str = new StringBuilder();
		if (matrix == null || matrix.length == 0)
			return;

		int i = matrix.length - 1;
		int j = matrix[0].length - 1;

		while (i > 0 && j > 0) {
			if (matrix[i][j] == 1 + matrix[i - 1][j - 1]) {
				str.append(s1.charAt(i - 1));
				i--;
				j--;
			} else if (matrix[i][j] == matrix[i - 1][j]) {
				i--;
			} else {
				j--;
			}
		}

		System.out.println(str.reverse().toString());

	}

	public int longestIncreasingSubsequence(int[] is) {

		if (is == null || is.length == 0)
			return 0;

		int[] result = new int[is.length];
		result[0] = 1;

		for (int i = 1; i < is.length; i++) {
			for (int j = 0; j < i; j++) {
				if (is[i] > is[j]) {
					result[i] = Math.max(1 + result[j], result[i]);
				}
			}
		}
		return result[result.length - 1];
	}

	public int maximumSumIncreasingSubsequence(int[] is) {
		if (is == null || is.length == 0)
			return 0;

		int[] result = new int[is.length];
		result[0] = is[0];
		
		int max = Integer.MIN_VALUE;

		for (int i = 1; i < is.length; i++) {
			for (int j = 0; j < i; j++) {
				if (is[j] < is[i]) {
					int tempResult = is[i] + result[j];
					result[i] = Math.max(result[i], tempResult);
				}
				max = Math.max(max, result[i]);
			}
		}
		
		return max;
	}
}
