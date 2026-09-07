class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;

        long total = 1; // empty subsequence

        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long newTotal = (total * 2 - last[index] + MOD) % MOD;

            last[index] = total;
            total = newTotal;
        }

        // Remove empty subsequence
        return (int) ((total - 1 + MOD) % MOD);
    }
}