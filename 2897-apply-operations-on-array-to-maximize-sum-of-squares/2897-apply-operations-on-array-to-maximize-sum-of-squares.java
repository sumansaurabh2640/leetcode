class Solution {
    public int maxSum(java.util.List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        int[] count = new int[31];

        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    count[bit]++;
                }
            }
        }

        long answer = 0;

        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 0; bit < 31; bit++) {
                if (count[bit] > 0) {
                    value |= (1L << bit);
                    count[bit]--;
                }
            }

            answer = (answer + value * value) % MOD;
        }

        return (int) answer;
    }
}