/**
 * www.lintcode.com/zh-cn/problem/classical-binary-search/
 * 经典二分查找，O(logn)复杂度, 常见二分版本
 */

package BinarySearch;

class Solution3 {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int findPosition(int[] nums, int target) {
    	if(nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	// 经典二分查找模板, 数组预先升序排练
    	int start = 0;
    	int end = nums.length - 1;
    	while (start < end) {
    		// 二分为左分量nums[start, mid]和右分量nums[mid+1, end]
    		int mid = start + (end - start) / 2;
    		
    		if (nums[mid] == target) {
    			return mid;
    		}
    		
    		// target在右分量中，更新start
    		if (nums[mid] < target) {
    			start = mid + 1;
    		}
    		
    		// target在左分量中，更新end
    		if (nums[mid] > target) {
    			end = mid - 1;
    		}
    		
    		// 更新start或end后，立即检查
    		if(nums[start] == target) {
    			return start;
    		}
    		if(nums[end] == target) {
    			return end;
    		}
    	}
    	return -1;
    	
    }
}

public class FindPosition {

	public static void main(String[] args) {
		
		int[] nums = new int[]{1, 2, 3, 4, 5, 6};
		System.out.println(new Solution3().findPosition(nums, 6));

	}

}
