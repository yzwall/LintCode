package doublepointers;
import java.util.Arrays;

/**
 * 双指针法求解，时间复杂度O(nlogn)，空间复杂度O(1)
 * 求两数之和小于等于target的所有组合数目
 * http://www.lintcode.com/en/problem/two-sum-less-than-or-equal-to-target/
 * @author yzwall
 */
class Solution14 {
	 public int twoSum5(int[] nums, int target) {
    	int pairs = 0;
    	if (nums == null || nums.length < 2) {
    		return pairs;
    	}
    	Arrays.sort(nums);
    	int i = 0;
    	int j = nums.length - 1;
    	while (i < j) {
    		// nums[i]的所有组合 = j - i
    		if (nums[i] + nums[j] <= target) {
    			pairs += j - i;
    			i++;
    		} else {
    			j--;
    		}
    	}
    	return pairs;
	 }
}

public class TwoSum_LessThanOrEqualToTarget_609 {
	public static void main(String[] args) {
	}
}
