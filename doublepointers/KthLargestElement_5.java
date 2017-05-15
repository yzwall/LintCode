package doublepointers;

import java.util.Arrays;

/**
 * 解法1：快速排序切分思想求第K大元素，时间复杂度O(n)，空间复杂度O(1)
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element/
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element-ii/
 * @author yzwall
 */
class Solution1 {
    public int kthLargestElement(int k, int[] nums) {
    	if (nums == null) {
    		return -1;
    	}
    	return quickSelect(nums, 0, nums.length - 1, k);
    } 
    /**
     * 快速排序切分思想求第k大元素，平均时间复杂度O(n)
     */
    private int quickSelect(int[] nums, int start, int end, int k) {
		if (start == end) {
			return nums[start];
		}
		
		int leftIndex = start;
		int rightIndex = end;
		// 切分尽量均匀
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
		
		// 第K大元素在pivot左边， 缩小问题规模
		if (start + k - 1 <= rightIndex) {
			return quickSelect(nums, start, rightIndex, k);
		}
		// 第K大元素在pivot右边， 缩小问题规模
		if (start + k - 1 >= leftIndex) {
			return quickSelect(nums, leftIndex, end, k - (leftIndex - start));
		}
		// 第K大元素在nums[rightIndex]~nums[leftIndex]之间
    	return nums[leftIndex - 1];
    }
}

/**
 * 解法2：先统一排序，时间复杂度O(nlogn)，空间复杂度O(1)
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element/
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element-ii/
 * @author yzwall
 */
class Solution2 {
    public int kthLargestElement(int k, int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
};

public class KthLargestElement_5 {
	public static void main(String[] args) {
		int[] nums = new int[]{3,44,38,5,47,15,26,26,27,2,46,4,19,50,48};
		System.out.println(new Solution1().kthLargestElement(3, nums));
	}
}
