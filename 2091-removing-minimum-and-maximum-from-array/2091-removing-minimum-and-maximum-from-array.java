class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indices of minimum and maximum
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make sure minIndex <= maxIndex
        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        // Option 1: Remove both from the front
        int fromFront = maxIndex + 1;

        // Option 2: Remove both from the back
        int fromBack = n - minIndex;

        // Option 3: Remove min from front and max from back
        int mixed = (minIndex + 1) + (n - maxIndex);

        return Math.min(fromFront, Math.min(fromBack, mixed));
    }
}