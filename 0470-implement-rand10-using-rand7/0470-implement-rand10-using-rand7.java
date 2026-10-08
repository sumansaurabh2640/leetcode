/**
 * The rand7() API is already defined.
 * int rand7();
 */

class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();

            if (num <= 40) {
                return (num - 1) % 10 + 1;
            }
        }
    }
}