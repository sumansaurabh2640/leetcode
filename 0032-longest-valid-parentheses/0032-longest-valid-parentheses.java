class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<Integer>();
        char ca[] = s.toCharArray();
        int maxlen = 0;
        st.push(-1);
        for(int i=0; i<ca.length; i++){
            if(ca[i]=='('){
                st.push(i);
            }
            else{
                st.pop();
                if(!st.isEmpty()){
                    maxlen = Math.max(maxlen, i-st.peek());
                }
                else{
                    st.push(i);
                }
            }
        }
        return maxlen;
    }
}