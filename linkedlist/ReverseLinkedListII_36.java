/**
 * 在指定范围内翻转单向链表
 * http://www.lintcode.com/en/problem/reverse-linked-list-ii/
 * @author yzwall
 */
package linkedlist;

class Solution3 {
	private LinkedListUtil test = new LinkedListUtil();
    /**
     * @param ListNode head is the head of the linked list 
     * @oaram m and n
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int left , int right) {
    	if (head == null || left < 1) {
    		return head;
    	}
    	if (left == 1 && right == 1) {
    		return head;
    	}
    	
    	ListNode leftPre = null, leftNode;
    	ListNode rightNode, rightNext;
    	ListNode pIter = head;
    	ListNode pre = null;
    	ListNode pNext = null;
    	for (int i = 0; i < left; i++) {
    		if (i == left - 1) {
    			leftPre = pre;
    		}
    		pre = pIter;
    		pIter = pIter.next;
    	}
    	leftNode = pre;
//    	test.visitNode("leftPre", leftPre);
//    	test.visitNode("leftNode", leftNode);
//    	test.visitNode("leftNext", leftNext);
    	
    	int reverseNum = right - left;
    	for (int i = 0; i < reverseNum; i++) {
    		pNext = pIter.next;
    		pIter.next = pre;
    		pre = pIter;
    		pIter = pNext; 
    	}
    	rightNode = pre;
    	rightNext = pIter; 
//    	test.visitNode("rightNode", rightNode);
//    	test.visitNode("rightNext", rightNext);
    	
    	if (left == 1) {
    		// 从表头开始翻转，翻转后更新表头
    		leftNode.next = rightNext;
    		return rightNode;
    	} else {
    		// 无需更新表头
        	leftPre.next = rightNode;
        	leftNode.next = rightNext;
        	return head;
    	}
    }
}


public class ReverseLinkedListII_36 {
	public static void main(String[] args) {
    	LinkedListUtil test = new LinkedListUtil();
    	ListNode head = test.createLinkedList(5);
    	test.traverseLinkedList(head);
    	
    	int left = 1; 
    	int right = 2;
    	ListNode head1 = new Solution3().reverseBetween(head, left, right);
    	test.traverseLinkedList(head1);
	}
}
