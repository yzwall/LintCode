/**
 * www.lintcode.com/zh-cn/problem/classical-binary-search/
 * 二分查找元素第一次出现位置，O(logn)复杂度, 九章算法二分查找模板
 */

package binarysearch;

/**
 * @author yzwall
 */
class Solution4 {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int binarySearch(int[] nums, int target) {
    	if(nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	// 经典二分查找模板, 数组预先升序排练
    	int start = 0;
    	int end = nums.length - 1;
    	int pos = Integer.MAX_VALUE;
    	while (start + 1 < end) {
    		
    		// 二分为左分量nums[start, mid]和右分量nums[mid+1, end]
    		int mid = start + (end - start) / 2;
    		
    		if (nums[mid] == target) {
    			// 临时记录匹配位置，不断迭代最小值
    			pos = Math.min(pos, mid);
    			end = mid;
    		}
    		
    		if (nums[mid] < target) {
    			start = mid;
    		}
    		
    		if (nums[mid] > target) {
    			end = mid;
    		}
    	}
    	
    	// 返回最先出现位置
		if(nums[start] == target) {
			return Math.min(pos, start);
		}
		if(nums[end] == target) {
			return Math.min(pos, end);
		}
    	return -1;
    }
}

public class FirstPosition_14 {

	public static void main(String[] args) {
		
		int[] nums = new int[]{8,8,8,8,8,8,8,10,13,14};
		System.out.println(new Solution4().binarySearch(nums, 8));

	}

}
