class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int answer = 0;

        for (int bit = 0; bit < 31; bit++) {
            int ones = 0;

            for (int num : nums) {
                if ((num & (1 << bit)) != 0) {
                    ones++;
                }
            }

            answer += ones * (n - ones);
        }

        return answer;
    }
}