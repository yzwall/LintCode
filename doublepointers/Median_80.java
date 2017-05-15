package doublepointers;

/**
 * 将求中位数转换为求第k大数问题，时间复杂度O(n)
 * http://www.lintcode.com/zh-cn/problem/median/
 * @author yzwall
 */
class Solution3 {
	public int median(int[] nums) {
		if (nums == null) {
			return -1;
		} 
		// 中位数是从大到小第[N/2] + 1大数
		int k = (nums.length / 2) + 1;
		return quickSelect(nums, 0, nums.length - 1, k);
	}
	
	private int quickSelect(int[] nums, int start, int end, int k) {
		if (start == end) {
			return nums[start];
		}
		
		int leftIndex = start;
		int rightIndex = end;
		int pivot = nums[start + (end - start) / 2];
		while (leftIndex <= rightIndex) {
			while (leftIndex <= rightIndex && nums[leftIndex] > pivot) {
				leftIndex++;
			}
			while (leftIndex <= rightIndex && nums[rightIndex] < pivot) {
				rightIndex--;
			}
			if (leftIndex <= rightIndex) {
				int temp = nums[leftIndex];
				nums[leftIndex] = nums[rightIndex];
				nums[rightIndex] = temp;
				leftIndex++;
				rightIndex--;
			}
		}
		
		if (start + k - 1 <= rightIndex) {
			return quickSelect(nums, start, rightIndex, k);
		}
		if (start + k - 1 >= leftIndex) {
			return quickSelect(nums, leftIndex, end, k - (leftIndex - start));
		}
		return nums[leftIndex - 1];
	}
}


public class Median_80 {

	public static void main(String[] args) {
		

	}

}
