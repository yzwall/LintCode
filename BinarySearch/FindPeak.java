/**
 * http://www.lintcode.com/zh-cn/problem/find-peak-element/
 * �ڼ�ֵ�����У�����˼���ҵ����⼫��ֵ��λ��
 * @author yzwall
 */
package BinarySearch;

class Solution12 {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {

    	if(A == null || A.length == 0) {
    		return -1;
    	}
    	
    	int start = 0;
    	int end = A.length - 1;
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		int zone = findZone(A, mid);
    		// �ҵ�����ֵ
    		if(zone == 0) {
    			return mid;
    		} 
    		if(zone == -1) {
    			// mid���½���
    			end = mid;
    		} else {
    			// mid��������
    			start = mid; 
    		}
    	}
    	return A[1];
    }
    
	/**
	 * �ж�A[index]�����ĸ�����
	 * ���ַ��ϸ�֤index����Խ��
	 * @param index Ԫ��λ��
	 * @return 0 ��ֵ
	 * 		   -1 λ��������
	 		   1 λ���½���
	 */
	private int findZone(int[] A, int index) {
		// find a peak
		if(A[index - 1] < A[index] && A[index + 1] < A[index]) {
			return 0;
		}
		if(A[index - 1] > A[index]) {
			return -1;
		} else {
			return 1;
		}
	}
}

public class FindPeak {

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 1, 3, 4, 5, 7, 6};
		System.out.println(new Solution12().findPeak(nums));
	}
}
