// class MajorityChecker {

//     public MajorityChecker(int[] arr) {
        
//     }
    
//     public int query(int left, int right, int threshold) {
        
//     }
// }

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */

 import java.util.*;

class MajorityChecker {

    private int[] arr;
    private Map<Integer, List<Integer>> positions;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        positions = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            positions.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int threshold) {

        // Find possible majority using Boyer-Moore
        int candidate = arr[left];
        int count = 0;

        for (int i = left; i <= right; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Count candidate in [left, right]
        List<Integer> list = positions.get(candidate);

        int first = lowerBound(list, left);
        int last = upperBound(list, right);

        int frequency = last - first;

        if (frequency >= threshold) {
            return candidate;
        }

        return -1;
    }

    private int lowerBound(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (list.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int upperBound(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (list.get(mid) > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}