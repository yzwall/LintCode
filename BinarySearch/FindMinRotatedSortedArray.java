/**
 * http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array/
 * 二分法在旋转数组中找出最小元素，时间复杂度o(logn)，暴力枚举复杂度o(n)
 * 需考虑跳出二分循环体的情况
 * @author yzwall
 */
package BinarySearch;

class Solution13 {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	int start = 0;
    	int end = nums.length - 1;
    	// 有序序列没有翻转
    	if (nums[start] < nums[end]) {
    		return nums[start];
    	}
    	
    	// 有序序列有翻转，且元素个数>2
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		// mid在上区域
    		if(nums[mid] > nums[start]) {
    			start = mid;
    		} else {
    			// mid在下区域
    			end = mid;
    			// 检查mid是否为最小值位置
    			if(nums[mid] < nums[mid - 1]) {
    				return nums[mid];
    			}
    		}
    	}
    	
    	// 有序序列有翻转, 且元素个数为1或2
    	return Math.min(nums[start], nums[end]);
    }
}


public class FindMinRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = new int[]{1};
		System.out.println(new Solution13().findMin(nums));
	}
}
