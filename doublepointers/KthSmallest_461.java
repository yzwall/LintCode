package doublepointers;

/**
 * 求第k小问题，快速排序切分思想解决，时间复杂度O(n)，空间复杂度O(1)
 * http://www.lintcode.com/en/problem/kth-smallest-numbers-in-unsorted-array/
 * @author yzwall
 */
class Solution4 {
    public int kthSmallest(int k, int[] nums) {
        if (nums == null) {
            return -1;
        }
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
    		while (leftIndex <= rightIndex && nums[leftIndex] < pivot) {
    			leftIndex++;
    		}
    		while (leftIndex <= rightIndex && nums[rightIndex] > pivot) {
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

public class KthSmallest_461 {
	public static void main(String[] args) {

	}
}
