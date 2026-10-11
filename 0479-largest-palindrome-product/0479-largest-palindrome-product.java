class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }

        long upper = (long) Math.pow(10, n) - 1;
        long lower = (long) Math.pow(10, n - 1);

        for (long left = upper; left >= lower; left--) {
            long palindrome = left;
            long x = left;

            while (x > 0) {
                palindrome = palindrome * 10 + x % 10;
                x /= 10;
            }

            for (long i = upper; i * i >= palindrome; i--) {
                if (palindrome % i == 0 && palindrome / i <= upper) {
                    return (int) (palindrome % 1337);
                }
            }
        }

        return 0;
    }
}