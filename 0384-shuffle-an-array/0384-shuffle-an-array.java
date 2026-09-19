// class Solution {

//     public Solution(int[] nums) {
        
//     }
    
//     public int[] reset() {
        
//     }
    
//     public int[] shuffle() {
        
//     }
// }

// /**
//  * Your Solution object will be instantiated and called as such:
//  * Solution obj = new Solution(nums);
//  * int[] param_1 = obj.reset();
//  * int[] param_2 = obj.shuffle();
//  */

import java.util.*;

class Solution {
    private int[] original;
    private Random random;

    public Solution(int[] nums) {
        original = nums.clone();
        random = new Random();
    }

    public int[] reset() {
        return original.clone();
    }

    public int[] shuffle() {
        int[] arr = original.clone();

        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }
}