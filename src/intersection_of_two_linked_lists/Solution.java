package intersection_of_two_linked_lists;


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
        	return null;
        
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 1;
        int lenB = 1;
        
        while(curA != null) {
    		curA = headA.next;
    		lenA ++;
    	}
        
        while(curA.next != null) {
    		curA = curA.next;
    		lenA ++;
    	}
        while(curB.next != null) {
    		curB = curB.next;
    		lenB ++;
    	}
        
        if(curA != curB)
        	return null;
        
        int gap = lenA - lenB;
        curA = headA;
        curB = headB;
        if(gap >= 0) {
        	for(int i = 0 ; i < gap && (curA != null); ++ i) {
        		curA = curA.next;
        	}
        } else {
        	for(int i = 0 ; i < -1 * gap && (curB != null); ++ i) {
        		curB = curB.next;
        	}
        }
        while(curA != null && curB != null) {
        	if(curA == curB)
        		return curA;
        	curA = curA.next;
        	curB = curB.next;
        }
        return null;
    }
}