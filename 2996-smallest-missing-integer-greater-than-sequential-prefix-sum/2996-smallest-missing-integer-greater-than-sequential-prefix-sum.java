class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        // Find longest sequential prefix
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Find smallest missing integer >= sum
        int answer = sum;

        while (contains(nums, answer)) {
            answer++;
        }

        return answer;
    }

    private boolean contains(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }

        return false;
    }
}