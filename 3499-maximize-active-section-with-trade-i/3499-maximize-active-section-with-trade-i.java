class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        // Count initial active sections
        int totalOnes = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') totalOnes++;
        }

        // Augment
        String t = "1" + s + "1";
        int m = t.length();

        // Build runs
        java.util.ArrayList<Character> chars = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> lens = new java.util.ArrayList<>();

        for (int i = 0; i < m; ) {
            char ch = t.charAt(i);
            int j = i;
            while (j < m && t.charAt(j) == ch) j++;

            chars.add(ch);
            lens.add(j - i);
            i = j;
        }

        int ans = totalOnes;

        // Look for pattern: 0-run, 1-run, 0-run
        for (int i = 1; i + 1 < chars.size(); i++) {
            if (chars.get(i) == '1'
                    && chars.get(i - 1) == '0'
                    && chars.get(i + 1) == '0') {

                int leftZero = lens.get(i - 1);
                int rightZero = lens.get(i + 1);

                ans = Math.max(ans, totalOnes + leftZero + rightZero);
            }
        }

        return Math.min(ans, n);
    }
}