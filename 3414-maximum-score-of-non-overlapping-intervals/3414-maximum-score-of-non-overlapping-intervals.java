import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // left
            arr[i][1] = intervals.get(i).get(1); // right
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = arr[i][0];
        }

        int[][] next = new int[n][5];

        for (int i = 0; i < n; i++) {
            int pos = lowerBound(starts, arr[i][1] + 1);
            next[i][0] = pos;
        }

        long[][] dp = new long[n + 1][5];
        List<Integer>[][] best = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                best[i][k] = new ArrayList<>();
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                long skipScore = dp[i + 1][k];
                long takeScore = arr[i][2] + dp[next[i][0]][k - 1];

                if (takeScore > skipScore) {
                    dp[i][k] = takeScore;
                    best[i][k] = new ArrayList<>(best[next[i][0]][k - 1]);
                    best[i][k].add(arr[i][3]);
                } else if (takeScore < skipScore) {
                    dp[i][k] = skipScore;
                    best[i][k] = new ArrayList<>(best[i + 1][k]);
                } else {
                    List<Integer> take = new ArrayList<>(best[next[i][0]][k - 1]);
                    take.add(arr[i][3]);

                    List<Integer> skip = best[i + 1][k];

                    Collections.sort(take);
                    Collections.sort(skip);

                    if (isLexicographicallySmaller(take, skip)) {
                        dp[i][k] = takeScore;
                        best[i][k] = take;
                    } else {
                        dp[i][k] = skipScore;
                        best[i][k] = new ArrayList<>(skip);
                    }
                }
            }
        }

        List<Integer> answer = best[0][4];
        Collections.sort(answer);

        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}