class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Total number of cells in the path must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Starting with ')' is impossible
        if (grid[0][0] == ')') {
            return false;
        }

        int maxBalance = m + n;

        boolean[][][] dp = new boolean[m][n][maxBalance];

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                for (int balance = 0; balance < maxBalance; balance++) {

                    if (grid[i][j] == '(') {

                        // '(' increases balance
                        if (balance > 0) {
                            if (i > 0 && dp[i - 1][j][balance - 1]) {
                                dp[i][j][balance] = true;
                            }

                            if (j > 0 && dp[i][j - 1][balance - 1]) {
                                dp[i][j][balance] = true;
                            }
                        }

                    } else {

                        // ')' decreases balance
                        // We need balance + 1, so check bounds
                        if (balance + 1 < maxBalance) {

                            if (i > 0 && dp[i - 1][j][balance + 1]) {
                                dp[i][j][balance] = true;
                            }

                            if (j > 0 && dp[i][j - 1][balance + 1]) {
                                dp[i][j][balance] = true;
                            }
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}