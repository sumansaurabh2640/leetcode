class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);

        for (int m = 60; m >= 2; m--) {
            long left = 2, right = (long) Math.pow(num, 1.0 / (m - 1)) + 1;

            while (left <= right) {
                long base = left + (right - left) / 2;
                long sum = 1;
                long term = 1;

                for (int i = 1; i < m; i++) {
                    if (term > (num - 1) / base) {
                        sum = num + 1;
                        break;
                    }

                    term *= base;

                    if (sum > num - term) {
                        sum = num + 1;
                        break;
                    }

                    sum += term;
                }

                if (sum == num) {
                    return String.valueOf(base);
                } else if (sum < num) {
                    left = base + 1;
                } else {
                    right = base - 1;
                }
            }
        }

        return String.valueOf(num - 1);
    }
}