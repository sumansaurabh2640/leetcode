class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // 1. Already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // 2. For length 2, the only substring containing
        // both characters is the entire string, which is forbidden.
        if (n == 2) {
            return -1;
        }

        // Find minimum and maximum characters
        char min = 'z';
        char max = 'a';

        for (int i = 0; i < n; i++) {
            min = (char) Math.min(min, s.charAt(i));
            max = (char) Math.max(max, s.charAt(i));
        }

        // 3. Minimum is already at beginning OR
        // maximum is already at end
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // 4. If min or max exists in the middle,
        // two operations are enough.
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == min || s.charAt(i) == max) {
                return 2;
            }
        }

        // 5. Minimum is only at the end and maximum
        // is only at the beginning.
        // This requires 3 operations.
        return 3;
    }
}