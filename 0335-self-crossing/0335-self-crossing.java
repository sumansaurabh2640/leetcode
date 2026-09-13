class Solution {
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;

        for (int i = 3; i < n; i++) {
            // Case 1: Current line crosses the line 3 steps before it
            if (distance[i] >= distance[i - 2]
                    && distance[i - 1] <= distance[i - 3]) {
                return true;
            }

            // Case 2: Current line crosses the line 4 steps before it
            if (i >= 4
                    && distance[i - 1] == distance[i - 3]
                    && distance[i] >= distance[i - 2] - distance[i - 4]) {
                return true;
            }

            // Case 3: Current line crosses the line 5 steps before it
            if (i >= 5
                    && distance[i - 2] >= distance[i - 4]
                    && distance[i - 1] <= distance[i - 3]
                    && distance[i - 1] >= distance[i - 3] - distance[i - 5]
                    && distance[i] >= distance[i - 2] - distance[i - 4]) {
                return true;
            }
        }

        return false;
    }
}