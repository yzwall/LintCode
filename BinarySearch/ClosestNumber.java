/**
 * http://www.lintcode.com/problem/closest-number-in-sorted-array/
 * ���ַ���������������������ҵ���ӽ�Ԫ��
 * ��ӽ�Ԫ�� = Min(MinMax��������Ԫ��, MaxMin�����ȳ���Ԫ��)
 */
package BinarySearch;

/**
 * @author yzwall
 */
class Solution {
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return ������������ӽ�target��Ԫ���±�
     */
    public int closestNumber(int[] A, int target) {
    	if(A == null || 0 == A.length) {
    		return -1;
    	}
    	
    	// �ҵ�target��MaxMin�����ȳ���Ԫ��
    	int index = findMaxMinIndex(A, target);
    	
    	if(0 == index) {
    		return 0;
    	}
    	
    	if(index == A.length) {
    		return A.length - 1;
    	}
    	
    	// ��ӽ�Ԫ�� = Min(MinMax��������Ԫ��, MaxMin�����ȳ���Ԫ��)
    	return target - A[index - 1] < A[index] - target ? index - 1 : index;
    }

    /**
     * @return ����target���ֵ�е���Сֵ�����еĵ�һ��Ԫ���±�(firstmaxmin)
     */
    private int findMaxMinIndex(int[] A, int target) {
    	int start = 0;
    	int end = A.length - 1;
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if(A[mid] == target) {
    			start = mid;
    		} else if(A[mid] < target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	
    	if(target <= A[start]) {
    		return start;
    	} 
    	
    	if(target <= A[end]) {
    		return end;
    	}
    	
    	return A.length;    	
	}
}

public class ClosestNumber {

	public static void main(String[] args) {
		
		int[] nums = new int[]{1, 3, 3, 5, 9};
		System.out.println(new Solution().closestNumber(nums, -1));
	}

}
