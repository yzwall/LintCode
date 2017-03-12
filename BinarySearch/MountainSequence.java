/**
 * http://www.lintcode.com/zh-cn/problem/maximum-number-in-mountain-sequence/
 * @author yzwall
 */

package BinarySearch;

class Solution11 {
	/**
	 * @param nums a mountain sequence which increase firstly and then decrease
	 * @return then mountain top
	 */
	public int mountainSequence(int[] nums) {
		
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int start = 0;
		int end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (getZone(nums, mid) == 0) {
				return nums[mid];
			} 
			// mid在上升区，抛弃左边上升区
			if(getZone(nums, mid) == -1) {
				start = mid;
			} else {
				// mid在下降区，抛弃右边下降区
				end = mid;
			}
		}
		
		// mauntain sequence为单调递减序列，峰值为首元素
		return nums[0];
		
	}
	
	/**
	 * 判断nums[index]属于哪个区域
	 * 二分法严格保证index不会越界
	 * @param index 元素位置
	 * @return 0 峰值
	 * 		   -1 位于上升区
	 		   1 位于下降区
	 */
	private int getZone(int[] nums, int index) {
		// 找到峰值
		if (nums[index - 1] < nums[index] && 
		    nums[index + 1] < nums[index]) {
			return 0;
		}
		// nums[index]在上升区
		if (nums[index - 1] < nums[index] && 
		    nums[index + 1] > nums[index]) {
			return -1;
		} else {
			return 1;
		}
	}
}

public class MountainSequence {

	public static void main(String[] args) {
		int[] nums = new int[]{10, 9, 8, 7};
		System.out.println(new Solution11().mountainSequence(nums));
	}

}
