class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new  Stack<Character>();
        char ca[] = s.toCharArray();
        int count = 0;
        for(int i=0; i<ca.length; i++){
            if(ca[i]=='('){
                st.push(ca[i]);
            }
            else if(!st.isEmpty())
                st.pop();
            else
                count++;    
        }
        count += st.size();
        return count;
    }
}