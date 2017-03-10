/**
 * http://www.lintcode.com/problem/closest-number-in-sorted-array/
 * 二分法查找最大在有序数组中找到最接近元素
 * 最接近元素 = Min(MinMax中最后出现元素, MaxMin中最先出现元素)
 */
package BinarySearch;

/**
 * @author yzwall
 */
class Solution {
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return 有序数组中最接近target的元素下标
     */
    public int closestNumber(int[] A, int target) {
    	if(A == null || 0 == A.length) {
    		return -1;
    	}
    	
    	// 找到target的MaxMin中最先出现元素
    	int index = findMaxMinIndex(A, target);
    	
    	if(0 == index) {
    		return 0;
    	}
    	
    	if(index == A.length) {
    		return A.length - 1;
    	}
    	
    	// 最接近元素 = Min(MinMax中最后出现元素, MaxMin中最先出现元素)
    	return target - A[index - 1] < A[index] - target ? index - 1 : index;
    }

    /**
     * @return 返回target最大值中的最小值集合中的第一个元素下标(firstmaxmin)
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
