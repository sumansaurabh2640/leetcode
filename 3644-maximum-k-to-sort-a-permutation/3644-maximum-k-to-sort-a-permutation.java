class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;

        // If already sorted, answer is 0
        boolean sorted = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // k must be a common set of bits between
        // every misplaced index and its current value.
        int ans = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                int common = i & nums[i];

                if (ans == -1) {
                    ans = common;
                } else {
                    ans &= common;
                }
            }
        }

        return ans;
    }
}