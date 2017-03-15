/**
 * 二分确定旋转数组最小值，根据最小值确定二分区间，最后进行简单二分查找
 * 访问数组时，谨慎考虑数组越界情况
 * http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/
 * @author yzwall
 */
package binarysearch;

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
    	
    	// 找到下区最小值，确定上区和下区边界
    	int minIndex = findMinIndex(A);
    	System.out.println("minIndex: " + minIndex);
    	
    	// minIndex可能返回0, 防止数组越界
    	if(minIndex - 1 >=0 && (A[minIndex - 1] < target || A[minIndex] > target)) {
    		return -1;
    	}
    	
    	int start = 0;
    	int end= A.length - 1;
    	/**
    	 * 确定二分区间
    	 * 需检查minIndex == 0时的情况
    	 */
    	if(A[start] > A[end] && A[start] <= target) {
    		end = minIndex - 1;
    	} else {
    		start = minIndex;
    	}
    	
    	// 简单二分查找
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
    	
        // 检查边界
        if(A[start] == target) {
        	return start;
        }
        
        if(A[end] == target) {
        	return end;
        }
    	
        return -1;
    }

    /**
     * 二分的根据是A[mid]与minIndex位置关系
     * @return 旋转数组A的最小值位置
     */
	private int findMinIndex(int[] A) {
		
		int start = 0;
		int end = A.length - 1;
		
		// 数组未翻转，最小值为首元素
		if(A[start] < A[end]) {
			return start;
		}
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (A[mid] < A[mid - 1]) {
				return mid;
			}
			/**
			 * 先判断A[mid]处于上区还是下区
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

public class SearchRotatedSotedArray_62 {

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 3};
		System.out.println(new Solution14().search(nums, 1));
	}
}
