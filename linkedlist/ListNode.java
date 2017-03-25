package linkedlist;

public class ListNode {
	int val;
	ListNode next;
	ListNode (int x) {
		this.val = x;
		this.next = null;
	}
}

class LinkedListUtil {
	public ListNode head;

	public LinkedListUtil() {
		this.head = new ListNode(1);
	}
	
	// ����1->2->3->...->end�ĵ�������
	public ListNode createLinkedList(int end) {
		if (end < 2) {
			return this.head;
		}
		
		ListNode pIter = this.head;
		for (int i = 2; i <= end; i++) {
			ListNode pNew = new ListNode(i);
			pIter.next = pNew;
			pIter = pIter.next;
		}
		return this.head;
	}
	
	// �ӱ�ͷ�����������
	public void traverseLinkedList(ListNode head) {
		ListNode pIter = head;
		while (pIter != null) {
			System.out.print(pIter.val + " ");
			pIter = pIter.next;
		}
		System.out.println("null");
	}
	
	// ��������ڵ�
	public void visitNode(String name, ListNode node) {
		if (node != null) {
			System.out.println(name + " " + node.val);
		} else {
			System.out.println(name + " " + "null");
		}
	}
	
	
}