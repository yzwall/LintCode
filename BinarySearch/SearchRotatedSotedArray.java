/**
 * ����ȷ����ת������Сֵ��������Сֵȷ���������䣬�����м򵥶��ֲ���
 * ��������ʱ��������������Խ�����
 * http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/
 * @author yzwall
 */
package BinarySearch;

class Solution14 {
    /** 
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int search(int[] A, int target) {
    	if(A == null || A.length == 0) {
    		return -1;
    	}
    	
    	// �ҵ�������Сֵ��ȷ�������������߽�
    	int minIndex = findMinIndex(A);
    	System.out.println("minIndex: " + minIndex);
    	
    	// minIndex���ܷ���0, ��ֹ����Խ��
    	if(minIndex - 1 >=0 && (A[minIndex - 1] < target || A[minIndex] > target)) {
    		return -1;
    	}
    	
    	int start = 0;
    	int end= A.length - 1;
    	/**
    	 * ȷ����������
    	 * ����minIndex == 0ʱ�����
    	 */
    	if(A[start] > A[end] && A[start] <= target) {
    		end = minIndex - 1;
    	} else {
    		start = minIndex;
    	}
    	
    	// �򵥶��ֲ���
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if(A[mid] == target) {
    			return mid;
    		}
    		if(A[mid] < target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	
        // ���߽�
        if(A[start] == target) {
        	return start;
        }
        
        if(A[end] == target) {
        	return end;
        }
    	
        return -1;
    }

    /**
     * ���ֵĸ�����A[mid]��minIndexλ�ù�ϵ
     * @return ��ת����A����Сֵλ��
     */
	private int findMinIndex(int[] A) {
		
		int start = 0;
		int end = A.length - 1;
		
		// ����δ��ת����СֵΪ��Ԫ��
		if(A[start] < A[end]) {
			return start;
		}
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < A[mid - 1]) {
				return mid;
			}
			/**
			 * ���ж�A[mid]����������������
			 */
			if(A[mid] > A[start]) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return A[start] < A[end] ? start : end;
	}
}

public class SearchRotatedSotedArray {

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 3};
		System.out.println(new Solution14().search(nums, 1));
	}
}
