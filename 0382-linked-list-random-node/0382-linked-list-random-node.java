// /**
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }
//  */
// class Solution {

//     public Solution(ListNode head) {
        
//     }
    
//     public int getRandom() {
        
//     }
// }

// /**
//  * Your Solution object will be instantiated and called as such:
//  * Solution obj = new Solution(head);
//  * int param_1 = obj.getRandom();
//  */

import java.util.*;

class Solution {
    private ListNode head;
    private Random random;

    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int result = head.val;
        ListNode current = head.next;
        int count = 2;

        while (current != null) {
            if (random.nextInt(count) == 0) {
                result = current.val;
            }

            current = current.next;
            count++;
        }

        return result;
    }
}