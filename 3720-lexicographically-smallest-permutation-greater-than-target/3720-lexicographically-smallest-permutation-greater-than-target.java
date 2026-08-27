class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        // Try to make the answer greater at position i.
        // We go from right to left because we want to keep
        // the longest possible prefix equal to target.
        for (int i = n - 1; i >= 0; i--) {

            int[] freq = new int[26];

            // Count all characters of s.
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            // Use target[0 ... i-1] as the equal prefix.
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int idx = target.charAt(j) - 'a';

                if (freq[idx] == 0) {
                    possible = false;
                    break;
                }

                freq[idx]--;
            }

            // Prefix cannot be formed.
            if (!possible) {
                continue;
            }

            // Find the smallest character greater than target[i].
            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    char[] ans = new char[n];

                    // 1. Copy equal prefix.
                    for (int j = 0; j < i; j++) {
                        ans[j] = target.charAt(j);
                    }

                    // 2. Make this position strictly greater.
                    ans[i] = (char) ('a' + c);
                    freq[c]--;

                    // 3. Fill the rest with smallest characters.
                    int pos = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            ans[pos++] = (char) ('a' + x);
                            freq[x]--;
                        }
                    }

                    return new String(ans);
                }
            }
        }

        return "";
    }
}