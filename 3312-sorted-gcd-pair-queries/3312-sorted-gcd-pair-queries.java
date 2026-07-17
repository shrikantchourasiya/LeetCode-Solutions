import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        // cntDiv[d] = numbers divisible by d
        int[] cntDiv = new int[max + 1];
        for (int d = 1; d <= max; d++) {
            for (int multiple = d; multiple <= max; multiple += d) {
                cntDiv[d] += freq[multiple];
            }
        }

        // gcdPairs[g] = pairs whose gcd is exactly g
        long[] gcdPairs = new long[max + 1];
        for (int g = max; g >= 1; g--) {
            long total = (long) cntDiv[g] * (cntDiv[g] - 1) / 2;
            for (int multiple = g * 2; multiple <= max; multiple += g) {
                total -= gcdPairs[multiple];
            }
            gcdPairs[g] = total;
        }

        // Prefix counts
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + gcdPairs[i];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // 0-indexed query
            int l = 1, r = max;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (prefix[mid] >= target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = l;
        }

        return ans;
    }
}