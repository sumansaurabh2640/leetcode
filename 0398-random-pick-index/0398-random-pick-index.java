// class Solution {

//     public Solution(int[] nums) {
        
//     }
    
//     public int pick(int target) {
        
//     }
// }

// /**
//  * Your Solution object will be instantiated and called as such:
//  * Solution obj = new Solution(nums);
//  * int param_1 = obj.pick(target);
//  */

 import java.util.*;

class Solution {
    private int[] nums;
    private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int result = -1;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;

                if (random.nextInt(count) == 0) {
                    result = i;
                }
            }
        }

        return result;
    }
}