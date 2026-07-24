import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;

        Set<Integer> pairXor = new HashSet<>();

        // Store XOR of every pair
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        // Store unique triplet XOR values
        BitSet seen = new BitSet();

        for (int x : pairXor) {
            for (int num : nums) {
                seen.set(x ^ num);
            }
        }

        return seen.cardinality();
    }
}