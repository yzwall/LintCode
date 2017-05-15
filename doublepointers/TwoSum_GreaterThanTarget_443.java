package doublepointers;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;

/**
 * 解法1：时间复杂度O(n^2)，空间复杂度O(1) 
 * 求两数之和大于target的组合数目
 * http://www.lintcode.com/en/problem/two-sum-greater-than-target/
 * @author yzwall
 */
class Solution11 {
    public int twoSum2(int[] nums, int target) {
    	int pairs = 0;
    	if (nums == null || nums.length < 2) {
    		return pairs;
    	}
    	Arrays.sort(nums);
    	
    	int i = 0, j = i + 1;
    	boolean find = false;
    	while (!find && i < nums.length) {
    		while (!find && j < nums.length) { 
    			if (nums[i] + nums[j] > target) {
    				find  = true;
    			}
    			if (!find) {
    				j++;
    			}
    		}
    		if (!find) {
    			i++;
    			j = i + 1;
    		}
    	}
    	if (!find) {
    		return 0;
    	}
    	
    	for (int ii = i + 1; ii < j - 1; ii++) {
    		for (int jj = ii + 1; jj < j; jj++) {
    			if (nums[ii] + nums[jj] > target) {
    				pairs++;
    			}
    		}
    	}
    	int count2 = (j - i) * (nums.length - j);
    	pairs += count2;
    	
        int count3 = nums.length - 1 - j;
    	for (int k = 1; k <= count3; k++) {
    	    pairs += k;
    	}
    	
    	return pairs;
    }
}

/**
 * 解法2：双指针法求解，时间复杂度O(logn)，空间复杂度O(1) 
 * 求两数之和大于target的组合数目
 * http://www.lintcode.com/en/problem/two-sum-greater-than-target/
 * @author yzwall
 */
class Solution13 {
	 public int twoSum2(int[] nums, int target) {
	    	int pairs = 0;
	    	if (nums == null || nums.length < 2) {
	    		return pairs;
	    	}
	    	Arrays.sort(nums);
	    	int i = 0;
	    	int j = nums.length - 1;
	    	while (i < j) {
	    		if (nums[i] + nums[j] > target) {
	    			// k ~ (i, j)内所有数必定满足nums[k] + nums[j] > target
	    			pairs += j - i;
	    			// nums[j]所有方案求解完毕，j--
	    			j--;
	    		} else {
	    			i++;
	    		}
	    	}
	    	return pairs;
	 }
}



public class TwoSum_GreaterThanTarget_443 {
	public static void main(String[] args) {
	}
}
