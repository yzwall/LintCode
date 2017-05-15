package doublepointers;
import java.util.HashSet;
import java.util.Arrays;

/**
 * 双指针找两数和等于target的不重复组合数目，时间复杂度O(n)，空间复杂度O(n)
 * 求两数之和等于target的所有不重复组合数目
 * http://www.lintcode.com/zh-cn/problem/two-sum-unique-pairs/
 * @author yzwall
 */
class Solution12 {
    public int twoSum6(int[] nums, int target) {
    	int pairs = 0;
    	if (nums == null || nums.length < 2) {
    		return pairs;
    	}
    	Arrays.sort(nums);
    	int i = 0;
    	int j = nums.length - 1;
    	HashSet<Integer> set = new HashSet<>();
    	while (i < j) {
    		// 如果a + b = target, a找到后，b无需再找
    		while (i < j && set.contains(nums[i])) {
    			i++;
    		}
    		while (i < j && set.contains(nums[j])) {
    			j--;
    		}
    		if (i < j) {
        		if (nums[i] + nums[j] == target) {
        			set.add(nums[i]);
        			set.add(nums[j]);
        			pairs++;
        		} else if (nums[i] + nums[j] > target) {
        			j--;
        		} else {
        			i++;
        		}
    		}
    	}
    	return pairs;
    }
}

public class TwoSum_UniquePairs_587 {
	public static void main(String[] args) {
		int[] nums = new int[]{1,1,2,45,46,46};
		int target = 47;
		Solution12 ss = new Solution12();
		System.out.println(ss.twoSum6(nums, target));
	}
}
