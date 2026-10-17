import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        Integer[] sorted = new Integer[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = nums[i];
        }

        Arrays.sort(sorted, (a, b) -> {
            int sa = digitSum(a);
            int sb = digitSum(b);

            if (sa != sb) {
                return Integer.compare(sa, sb);
            }

            return Integer.compare(a, b);
        });

        Map<Integer, Integer> position = new HashMap<>();

        for (int i = 0; i < n; i++) {
            position.put(nums[i], i);
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == sorted[i]) {
                continue;
            }

            int j = position.get(sorted[i]);

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;

            position.put(nums[j], j);
            position.put(nums[i], i);

            swaps++;
        }

        return swaps;
    }

    private int digitSum(int x) {
        int sum = 0;

        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }

        return sum;
    }
}