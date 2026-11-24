
import java.math.BigInteger;

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            BigInteger product = BigInteger.ONE;
            BigInteger lcm = BigInteger.ONE;
            int gcd = 0;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                product = product.multiply(BigInteger.valueOf(x));

                gcd = gcd(gcd, x);

                BigInteger bg = BigInteger.valueOf(x);
                lcm = lcm.divide(lcm.gcd(bg)).multiply(bg);

                BigInteger required = lcm.multiply(
                    BigInteger.valueOf(gcd)
                );

                if (product.equals(required)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
