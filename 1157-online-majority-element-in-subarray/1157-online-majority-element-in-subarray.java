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
    private Random random = new Random();

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        positions = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            positions.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        int length = right - left + 1;

        for (int t = 0; t < 20; t++) {
            int index = left + random.nextInt(length);
            int candidate = arr[index];

            List<Integer> list = positions.get(candidate);

            int count = upperBound(list, right) - lowerBound(list, left);

            if (count >= threshold) {
                return candidate;
            }
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