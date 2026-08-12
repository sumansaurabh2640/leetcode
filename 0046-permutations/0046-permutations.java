// import java.util.*;

// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> result = new ArrayList<>();
//         boolean[] used = new boolean[nums.length];

//         backtrack(nums, used, new ArrayList<>(), result);

//         return result;
//     }

//     private void backtrack(int[] nums, boolean[] used,
//                            List<Integer> current,
//                            List<List<Integer>> result) {

//         if (current.size() == nums.length) {
//             result.add(new ArrayList<>(current));
//             return;
//         }

//         for (int i = 0; i < nums.length; i++) {
//             if (used[i]) {
//                 continue;
//             }

//             used[i] = true;
//             current.add(nums[i]);

//             backtrack(nums, used, current, result);

//             current.remove(current.size() - 1);
//             used[i] = false;
//         }
//     }
// }
import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(nums, 0, result);

        return result;
    }

    private void backtrack(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();

            for (int num : nums) {
                list.add(num);
            }

            result.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // Swap
            int temp = nums[start];
            nums[start] = nums[i];
            nums[i] = temp;

            // Generate remaining permutations
            backtrack(nums, start + 1, result);

            // Backtrack: undo swap
            temp = nums[start];
            nums[start] = nums[i];
            nums[i] = temp;
        }
    }
}