
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000;

        // cost[i][j] = minimum changes to make s[i...j] semi-palindromic
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
        }

        // Precompute costs for every substring.
        for (int len = 2; len <= n; len++) {
            for (int start = 0; start + len <= n; start++) {
                int end = start + len - 1;

                // Try every proper divisor of len.
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group.
                    for (int r = 0; r < d; r++) {
                        int left = r;
                        int right = r + ((len - 1 - r) / d) * d;

                        while (left < right) {
                            if (s.charAt(start + left) !=
                                s.charAt(start + right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    cost[start][end] =
                        Math.min(cost[start][end], changes);
                }
            }
        }

        // dp[g][i]: minimum changes for first i characters
        // divided into exactly g semi-palindromes.
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int g = 1; g <= k; g++) {
            for (int i = 2 * g; i <= n; i++) {
                for (int j = 2 * (g - 1); j <= i - 2; j++) {
                    if (dp[g - 1][j] == INF) {
                        continue;
                    }

                    dp[g][i] = Math.min(
                        dp[g][i],
                        dp[g - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
