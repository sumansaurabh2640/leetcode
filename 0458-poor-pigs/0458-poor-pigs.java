class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int rounds = minutesToTest / minutesToDie;
        int pigs = 0;
        int states = 1;

        while (states < buckets) {
            states *= rounds + 1;
            pigs++;
        }

        return pigs;
    }
}