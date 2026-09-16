class Solution {
    private static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[k + 1][n];

        // 0 segments can be formed in 1 way
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int seg = 1; seg <= k; seg++) {
            long prefix = 0;

            for (int i = 1; i < n; i++) {
                // Start a new segment at point i-1
                prefix = (prefix + dp[seg - 1][i - 1]) % MOD;

                // Either extend an existing segment or start a new one
                dp[seg][i] = (dp[seg][i - 1] + prefix) % MOD;
            }
        }

        return (int) dp[k][n - 1];
    }
}