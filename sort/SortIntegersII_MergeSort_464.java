/**
 * http://www.lintcode.com/en/problem/sort-integers-ii/
 * �鲢����һ���������飨����
 * @author yzwall
 */
package sort;

class Solution {
	
    public void sortIntegers2(int[] A) {
    	if (A == null || A.length == 0) {
    		return;
    	}
    	
    	// temp�����Ź鲢����, �ռ临�Ӷ�O(n)
    	int[] temp = new int[A.length];
    	mergeSort(A, 0, A.length - 1, temp);
    }
    
    /**
     * �鲢���򣺷��εݹ� + ��·�鲢��
     * ʱ�临�Ӷ�O(nlogn)
     * �ռ临�Ӷ�O(logn + n)
     */
    private void mergeSort(int[] A, int start, int end, int[] temp) {
    	// �ݹ���� ����Ԫ�ز����鲢����
    	if (start >= end) {
    		return;
    	}
    	
    	/**
    	 * divide & conquer
    	 * �ݹ����Ϊlogn
    	 * �ݹ�ʱ�临�Ӷ�O(logn), �ռ临�Ӷ�O(logn)
    	 */
    	int mid = start + (end - start) / 2;
    	// ��A[start, mid]�鲢����Ϊ��������
    	mergeSort(A, start, mid, temp);
    	// ��A[mid + 1, end]�鲢����Ϊ��������
    	mergeSort(A, mid + 1, end, temp);
    	
    	// merge����, ʱ�临�Ӷ�O(n)
    	merge(A, start, mid, end, temp);
    }
    
    /**
     * ��·�鲢���鲢A[start, mid]��A[mid + 1, end]Ϊ��������A[start, end]
     * ʱ�临�Ӷ�O(n)�� �ռ临�Ӷ�O(n)
     * @param A ���鲢����
     * @param temp ԭ�ع鲢�������ռ�
     */
    private void merge(int[] A, int start, int mid, int end, int[] temp) {
    	int leftIndex = start;
    	int rightIndex = mid + 1;
    	int index = start;
    	
    	while (leftIndex <= mid && rightIndex <= end) {
    		// ����鲢�����ȹ鲢��СԪ��
    		if (A[leftIndex] < A[rightIndex]) {
    			temp[index++] = A[leftIndex++];
    		} else {
    			temp[index++] = A[rightIndex++];
    		}
    	}
    	
    	while (leftIndex <= mid) {
    		// �Ҳ�A[mid + 1, end]�Ѿ��鲢��ϣ��鲢���A[start, mid]ʣ������
    		temp[index++] = A[leftIndex++];
    	}
    	
    	while (rightIndex <= end) {
    		// ���A[start, mid]�Ѿ��鲢��ϣ��鲢�Ҳ�A[mid + 1, end]ʣ������ 
    		temp[index++] = A[rightIndex++];
    	}
    	
    	// temp�Ѿ��ɹ��鲢A[start, mid]��A[mid + 1, end]��ԭ�ظ���ԭ����A[start, end]
    	for (int i = start; i <= end; i++) {
    		A[i] = temp[i];
    	}	
    }
}



public class SortIntegersII_MergeSort_464 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		Solution sort = new Solution();
		sort.sortIntegers2(A);
		//sort.print(A);

	}

}
