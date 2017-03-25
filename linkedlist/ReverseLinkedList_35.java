/**
 * 反转单向链表
 * http://www.lintcode.com/en/problem/reverse-linked-list/
 * @author yzwall
 */
package linkedlist;

class Solution2 {
    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode pIter = head;
    	ListNode pre = null;
    	ListNode pNext = null;
    	while (pIter != null) {
    		// pNext表示下一个待翻转节点
    		pNext = pIter.next;
    		// 翻转pIter
    		pIter.next = pre;
    		// 更新前驱
    		pre = pIter;
    		// 更新pIter, 准备进行下一次翻转
    		pIter = pNext;
    	}
    	// 更新表头
    	head = pre;	
    	return head;
    }
}



public class ReverseLinkedList_35 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
