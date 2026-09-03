class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;

        for (int x : nums1) {
            min = Math.min(min, x);
        }

        // If minimum is odd, always possible.
        if (min % 2 == 1) {
            return true;
        }

        // If minimum is even, every element must be even.
        for (int x : nums1) {
            if (x % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}