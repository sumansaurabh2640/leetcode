class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1_000_000_000;

        int[] best = new int[n];
        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;
        int minLen = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, len + best[left - 1]);
                }

                minLen = Math.min(minLen, len);
            }

            best[right] = minLen;
        }

        return answer == INF ? -1 : answer;
    }
}