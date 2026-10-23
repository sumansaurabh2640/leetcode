class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Already sorted
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

        // Case 1: sorted = left rotate nums by k
        int pos0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos0 = i;
                break;
            }
        }

        boolean possible = true;

        // If 0 is at position k, check whether
        // nums[k], nums[k+1], ... forms 0,1,2,... cyclically.
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) {
                possible = false;
                break;
            }
        }

        if (possible) {
            int k = pos0;

            // Minimum way to perform a left rotation by k:
            // k left rotations OR
            // 2 reverses + (n-k) left rotations
            int cost = Math.min(k, n - k + 2);

            return cost;
        }

        // Case 2: sorted = reverse(nums) rotated left by k
        //
        // reverse(nums)[k] must be 0.
        // Position of 0 in reverse(nums):
        int revPos0 = n - 1 - pos0;

        possible = true;

        for (int i = 0; i < n; i++) {
            int index = (revPos0 + i) % n;
            int value = nums[n - 1 - index];

            if (value != i) {
                possible = false;
                break;
            }
        }

        if (possible) {
            int k = revPos0;

            // One reverse + k left rotations.
            // Equivalently, one reverse + (n-k) left rotations.
            int cost = 1 + Math.min(k, n - k);

            return cost;
        }

        return -1;
    }
}