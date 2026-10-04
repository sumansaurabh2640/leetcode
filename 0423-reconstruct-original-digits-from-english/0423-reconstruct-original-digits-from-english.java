class Solution {
    public String originalDigits(String s) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int[] digit = new int[10];

        // Unique characters
        digit[0] = count['z' - 'a']; // zero
        digit[2] = count['w' - 'a']; // two
        digit[4] = count['u' - 'a']; // four
        digit[6] = count['x' - 'a']; // six
        digit[8] = count['g' - 'a']; // eight

        // Remove letters of these digits
        remove(count, "zero", digit[0]);
        remove(count, "two", digit[2]);
        remove(count, "four", digit[4]);
        remove(count, "six", digit[6]);
        remove(count, "eight", digit[8]);

        // Remaining digits
        digit[3] = count['h' - 'a']; // three
        remove(count, "three", digit[3]);

        digit[5] = count['f' - 'a']; // five
        remove(count, "five", digit[5]);

        digit[7] = count['s' - 'a']; // seven
        remove(count, "seven", digit[7]);

        digit[1] = count['o' - 'a']; // one
        remove(count, "one", digit[1]);

        digit[9] = count['i' - 'a']; // nine

        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < digit[i]; j++) {
                result.append(i);
            }
        }

        return result.toString();
    }

    private void remove(int[] count, String word, int times) {
        for (char c : word.toCharArray()) {
            count[c - 'a'] -= times;
        }
    }
}