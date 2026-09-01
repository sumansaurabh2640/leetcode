import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sr = 0, sc = 0;
        int litterCount = 0;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        // Find start and assign an ID to every litter cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;

        /*
         * visited[r][c][mask][energy]
         *
         * energy ranges from 0 to energy.
         */
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        // Queue stores: row, col, mask, remaining energy
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        visited[sr][sc][0][energy] = true;
        queue.offer(new int[]{sr, sc, 0, energy});

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {
                int[] cur = queue.poll();

                int r = cur[0];
                int c = cur[1];
                int mask = cur[2];
                int e = cur[3];

                // All litter collected
                if (mask == targetMask) {
                    return moves;
                }

                // If no energy remains, we cannot move.
                // We could only have continued if we were on R,
                // where energy would already have been reset.
                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = e - 1;

                    // Collect litter if present
                    int newMask = mask;

                    if (classroom[nr].charAt(nc) == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    // Reset energy on R
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newMask][newEnergy]) {
                        visited[nr][nc][newMask][newEnergy] = true;
                        queue.offer(new int[]{
                                nr, nc, newMask, newEnergy
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}