class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers are never palindromes
        // Numbers ending in 0 are not palindromes unless x is 0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;

        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // Even number of digits
        // Odd number of digits: ignore the middle digit
        return x == reversed || x == reversed / 10;
    }
}