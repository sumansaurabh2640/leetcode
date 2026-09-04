import java.util.*;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int number = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }
            else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            }
            else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            }
            else if (c == '(') {
                // Save current result and sign
                stack.push(result);
                stack.push(sign);

                result = 0;
                sign = 1;
            }
            else if (c == ')') {
                // Complete current number
                result += sign * number;
                number = 0;

                // Sign before '('
                int previousSign = stack.pop();

                // Result before '('
                int previousResult = stack.pop();

                result = previousResult + previousSign * result;
            }
        }

        // Add the last number
        result += sign * number;

        return result;
    }
}