/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int prev = -1;

        int minDist = Integer.MAX_VALUE;

        ListNode previous = head;
        ListNode current = head.next;

        int index = 1;

        while (current.next != null) {

            ListNode next = current.next;

            // Check if current node is a critical point
            boolean isCritical =
                    (current.val > previous.val && current.val > next.val) ||
                    (current.val < previous.val && current.val < next.val);

            if (isCritical) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // We already have a previous critical point
                if (prev != -1) {
                    minDist = Math.min(minDist, index - prev);
                }

                prev = index;
            }

            previous = current;
            current = next;
            index++;
        }

        // Fewer than two critical points
        if (first == -1 || prev == first) {
            return new int[]{-1, -1};
        }

        int maxDist = prev - first;

        return new int[]{minDist, maxDist};
    }
}