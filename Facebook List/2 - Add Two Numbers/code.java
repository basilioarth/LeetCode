/**
 * Status: Accepted
 * 362 / 362 test cases passed
 * Runtime: 2 ms
 * Memory Usage: 39.1 MB
 * Details:
 * - Your runtime is faster than 70.35% of Java online submissions for Add Two Numbers.
 * - Your memory usage are less than 88.66% of Java online submissions for Add Two Numbers.
*/ 

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int overflowingValue = 0;
        int currentValue = 0;
        
        ListNode nodesList = new ListNode(); 
        ListNode initialNode = nodesList;
        
        while(l1 != null || l2 != null){
            currentValue = 0;
            
            if(l1 != null) currentValue += l1.val;
            if(l2 != null) currentValue += l2.val;
            currentValue += overflowingValue;
            
            if(currentValue <= 9){
                nodesList.val = currentValue;
                overflowingValue = 0;
            } else {
                overflowingValue = currentValue/10;
                nodesList.val = currentValue-10;
            }
            
            if((l1 != null && l1.next != null) || (l2 != null && l2.next != null) || overflowingValue != 0){
                nodesList.next = new ListNode();
                nodesList = nodesList.next;
            } 
            
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        
        if(overflowingValue != 0) nodesList.val = overflowingValue;
        
    return initialNode;
    }
}