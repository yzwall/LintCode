/**
 * ��ת��������
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
    		// pNext��ʾ��һ������ת�ڵ�
    		pNext = pIter.next;
    		// ��תpIter
    		pIter.next = pre;
    		// ����ǰ��
    		pre = pIter;
    		// ����pIter, ׼��������һ�η�ת
    		pIter = pNext;
    	}
    	// ���±�ͷ
    	head = pre;	
    	return head;
    }
}



public class ReverseLinkedList_35 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
