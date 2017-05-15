package doublepointers;
import java.util.HashSet;
import java.util.Arrays;

/**
 * 解法1：HashSet去重，时间复杂度O(n)，空间复杂度O(n)
 * http://www.lintcode.com/zh-cn/problem/remove-duplicate-numbers-in-array/
 * @author yzwall
 */
class Solution9 {
	public int deduplication(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		HashSet<Integer> set = new HashSet<>();
		for (int i : nums) {
			set.add(i);
		}
		int k = 0;
		for (int i : set) {
			nums[k++] = i;
		}
		return k;
	}
}

/**
 * 解法2：双指针法去重，时间复杂度O(nlogn)，空间复杂度O(1)
 * http://www.lintcode.com/zh-cn/problem/remove-duplicate-numbers-in-array/
 * @author yzwall
 */
class Solution10 {
	public int deduplication(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 1;
		}
		Arrays.sort(nums);
		int i = 0;
		int j = i + 1;
		while (j < nums.length) {
			if (nums[i] == nums[j]) {
				j++;
			} else {
				nums[++i] = nums[j++];
			} 
		}
		return i + 1;
	}
}

public class Deduplication_521 {
	public static void main(String[] args) {
	}
}
