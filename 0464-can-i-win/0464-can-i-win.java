import java.util.*;

class Solution {
    private Map<Integer, Boolean> memo;
    private int max;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        max = maxChoosableInteger;

        if (desiredTotal <= 0) {
            return true;
        }

        int sum = max * (max + 1) / 2;

        if (sum < desiredTotal) {
            return false;
        }

        memo = new HashMap<>();
        return canWin(0, desiredTotal);
    }

    private boolean canWin(int mask, int target) {
        if (memo.containsKey(mask)) {
            return memo.get(mask);
        }

        for (int i = 1; i <= max; i++) {
            int bit = 1 << (i - 1);

            if ((mask & bit) != 0) {
                continue;
            }

            if (i >= target || !canWin(mask | bit, target - i)) {
                memo.put(mask, true);
                return true;
            }
        }

        memo.put(mask, false);
        return false;
    }
}
