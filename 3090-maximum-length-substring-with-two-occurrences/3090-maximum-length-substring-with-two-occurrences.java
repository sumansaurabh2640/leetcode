class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            int ch = s.charAt(right) - 'a';
            count[ch]++;

            while (count[ch] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}