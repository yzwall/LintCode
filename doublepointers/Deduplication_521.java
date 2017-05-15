package doublepointers;
import java.util.HashSet;
import java.util.Arrays;

/**
 * �ⷨ1��HashSetȥ�أ�ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)
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
 * �ⷨ2��˫ָ�뷨ȥ�أ�ʱ�临�Ӷ�O(nlogn)���ռ临�Ӷ�O(1)
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
