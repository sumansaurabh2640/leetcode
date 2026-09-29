// class Solution {
//     private void reverse(int[] nums, int left, int right) {
//         while (left < right) {
//             int temp = nums[left];
//             nums[left] = nums[right];
//             nums[right] = temp;

//             left++;
//             right--;
//         }
//     }
//     public void rotate(int[] nums, int k) {
//         int n = nums.length;

//         k = k % n;

//         // Reverse the entire array
//         reverse(nums, 0, n - 1);

//         // Reverse the first k elements
//         reverse(nums, 0, k - 1);

//         // Reverse the remaining elements
//         reverse(nums, k, n - 1);
//     }


// }



                                                // Cyclic Replacements Approach    
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int count = 0;
        int start = 0;

        while (count < n) {
            int current = start;
            int prev = nums[start];

            do {
                int next = (current + k) % n;

                int temp = nums[next];
                nums[next] = prev;
                prev = temp;

                current = next;
                count++;

            } while (current != start);

            start++;
        }
    }
}                                                