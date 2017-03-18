/**
 * http://www.lintcode.com/en/problem/sort-integers-ii/
 * ��������һ�����飨����
 * @author yzwall
 */
package sort;

class Solution1 {
	
    public void sortIntegers2(int[] A) {
    	if (A == null || A.length == 0) {
    		return;
    	}
    	
    	quickSort(A, 0, A.length - 1);
    }
    
    private void quickSort(int[]A, int start, int end) {
    	//���ݹ���ڡ���Ԫ�ز�������
    	if (start >= end) {
    		return;
    	}
    	
    	/**
    	 * �зֲ���ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(1)
    	 * �зֵ�pivotȡ����ֵ���������±꣬��֤ pivot��ࡰС�����Ҳࡰ��
    	 */
    	int leftIndex = start;
    	int rightIndex = end;
    	int pivot = A[start + (end - start) / 2];
    	
    	// leftIndex <= rightIndex, < s����ջ���
    	while (leftIndex <= rightIndex) {
    		// �����Ѱ�Ҳ��Ϸ����� A[leftIndex] < pivot����֤�з־���
    		while (leftIndex <= rightIndex && A[leftIndex] < pivot) {
    			leftIndex++;
    		}
    		
    		// ���Ҳ�Ѱ�Ҳ��Ϸ����� A[rightIndex] > pivot����֤�з־���
    		while (leftIndex <= rightIndex && A[rightIndex] > pivot) {
    			rightIndex--;
    		}
    		
    		// �ҵ����Ϸ����������Ϸ��������Ӧ������
    		if (leftIndex <= rightIndex) {
    			int temp = A[leftIndex];
    			A[leftIndex] = A[rightIndex];
    			A[rightIndex] = temp;
    			// �������Ҳ��Ϸ���
    			leftIndex++;
    			rightIndex--;
    		}
    	}
    	
    	/**
    	 * ����ѭ����leftIndex��rightIndex�ѻ���λ��
    	 * ����ʱ�临�Ӷ�O(logn), �ռ临�Ӷ�O(1)
    	 */
    	quickSort(A, start, rightIndex);
    	quickSort(A, leftIndex, end);
    }
}


public class SortIntegersII_QuickSort_464 {

	public static void main(String[] args) {
		
		int[] A = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		Solution1 sort = new Solution1();
		sort.sortIntegers2(A);
		//sort.print(A, 0, A.length - 1);
	}

}
