package doublepointers;
import java.util.Arrays;

/**
 * 解法1：暴力时间复杂度O(n^2)
 * 求两数之和最接近target，求最近距离
 * http://www.lintcode.com/en/problem/two-sum-closest-to-target/
 * @author yzwall
 */
class Solution15 {
    public int twoSumClosest(int[] nums, int target) {
    	if (nums == null || nums.length < 2) {
    		return target;
    	}
    	
    	int min = Integer.MAX_VALUE;
    	int temp;
    	for (int i = 0; i < nums.length; i++) {
    		for (int j = i + 1; j < nums.length; j++) {
    			temp = target - (nums[i] + nums[j]);
    			min = Math.min(min, Math.abs(temp));
    		}
    	}
    	return min;
    }
}


/**
 * 解法2：双指针法求解，时间复杂度O(nlogn)
 * 求两数之和最接近target，求最近距离
 * http://www.lintcode.com/en/problem/two-sum-closest-to-target/
 * @author yzwall
 */
class Solution16 {
    public int twoSumClosest(int[] nums, int target) {
    	if (nums == null || nums.length < 2) {
    		return target;
    	}
    	
    	Arrays.sort(nums);
    	int i = 0;
    	int j = nums.length - 1;
    	int min = Integer.MAX_VALUE;
    	int temp;
    	while (i < j) {
    		temp = Math.abs(target - (nums[i] + nums[j]));
    		if (temp == 0) {
    			return 0;
    		}
    		min = Math.min(min, temp);
    		if (nums[i] + nums[j] > target) {
    			j--;
    		} else {
    			i++;
    		}
    	}
    	return min;
   }
}




public class TwoSum_ClosestToTarget_533 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
