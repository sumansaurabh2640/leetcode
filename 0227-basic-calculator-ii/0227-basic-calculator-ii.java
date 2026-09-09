class Solution {
    public int calculate(String s) {
        int result = 0;
        int current = 0;
        int last = 0;
        char operation = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                current = current * 10 + (c - '0');
            }

            // Process when we reach an operator or the end
            if ((c == '+' || c == '-' || c == '*' || c == '/') 
                    && !Character.isDigit(c) || i == s.length() - 1) {

                if (operation == '+') {
                    result += last;
                    last = current;
                } 
                else if (operation == '-') {
                    result += last;
                    last = -current;
                } 
                else if (operation == '*') {
                    last = last * current;
                } 
                else if (operation == '/') {
                    last = last / current;
                }

                operation = c;
                current = 0;
            }
        }

        return result + last;
    }
}