class Solution {
    public int findNthDigit(int n) {
        long digits = 1;
        long count = 9;
        long start = 1;

        while (n > digits * count) {
            n -= digits * count;
            digits++;
            count *= 10;
            start *= 10;
        }

        long number = start + (n - 1) / digits;
        int index = (int) ((n - 1) % digits);

        return String.valueOf(number).charAt(index) - '0';
    }
}