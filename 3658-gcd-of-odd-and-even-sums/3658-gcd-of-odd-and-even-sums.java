class Solution {

    public int gcdOfOddEvenSums(int n) {

        int oddSum = 0;
        int evenSum = 0;

        // Sum of first n odd and even numbers
        for (int i = 1; i <= n; i++) {
            oddSum += (2 * i - 1);
            evenSum += (2 * i);
        }

        // Euclidean Algorithm for GCD
        while (evenSum != 0) {
            int temp = evenSum;
            evenSum = oddSum % evenSum;
            oddSum = temp;
        }

        return oddSum;
    }
}