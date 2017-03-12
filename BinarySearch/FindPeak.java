/**
 * http://www.lintcode.com/zh-cn/problem/find-peak-element/
 * 在极值序列中，二分思想找到任意极大值的位置
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
    		// 找到极大值
    		if(zone == 0) {
    			return mid;
    		} 
    		if(zone == -1) {
    			// mid在下降区
    			end = mid;
    		} else {
    			// mid在上升区
    			start = mid; 
    		}
    	}
    	return A[1];
    }
    
	/**
	 * 判断A[index]属于哪个区域
	 * 二分法严格保证index不会越界
	 * @param index 元素位置
	 * @return 0 峰值
	 * 		   -1 位于上升区
	 		   1 位于下降区
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
