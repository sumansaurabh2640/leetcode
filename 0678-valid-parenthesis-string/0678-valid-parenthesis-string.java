class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } 
            else if (c == ')') {
                minOpen--;
                maxOpen--;
            } 
            else { // '*'
                minOpen--;  // '*' acts as ')'
                maxOpen++;  // '*' acts as '('
            }

            // Too many ')' even in the best case
            if (maxOpen < 0) {
                return false;
            }

            // We cannot have negative unmatched '('
            minOpen = Math.max(minOpen, 0);
        }

        // We need at least one possibility with zero unmatched '('
        return minOpen == 0;
    }
}