/**
 * �ж������Ƿ���ģ�������ڵ�������ջ��Ȼ�󰤸���ջ�Ƚ�
 * http://www.lintcode.com/en/problem/palindrome-linked-list/
 * @author yzwall
 */
package linkedlist;
import java.util.ArrayDeque;

class Solution1 {
    /**
     * @param head a ListNode
     * @return a boolean
     */
    public boolean isPalindrome(ListNode head) {
    	if (head == null || head.next == null) {
    		return true;
    	}
    	
    	ArrayDeque<ListNode> stack = new ArrayDeque<>();
    	ListNode pIter = head;
    	while (pIter != null) {
    		stack.push(pIter);
    		pIter = pIter.next;
    	}
    	
    	pIter = head;
    	while(pIter != null) {
    		if (pIter.val != stack.pop().val) {
    			return false;
    		}
    		pIter = pIter.next;
    	}
    	return true;
    }
}

public class PalindromeLinkedList_223 {
	public static void main(String[] args) {
		
	}
}
