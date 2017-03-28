/**
 * http://www.lintcode.com/en/problem/window-sum/
 * @author yzwall
 */
package array;

class Solution1 {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
    	
    	if (nums.length == 0) {
    		return new int[0];
    	}
    	
    	if (k > nums.length) {
    		return new int[0];
    	}
    	int loop = nums.length - k + 1;
    	int[] winSums = new int[loop];
    	
    	for (int i = 0; i < k; i++) {
    		winSums[0] += nums[i];
    	}
    	
    	for (int i = 1; i < loop; i++) {
    		winSums[i] = winSums[i - 1] + nums[i + k - 1] - nums[i - 1];
    	}
    	
    	for (int i : winSums) {
    		System.out.println(i);
    	}
    	
    	return winSums;
    }
}


public class WindowSum_604 {
	public static void main(String[] args) {
		int[] nums = new int[]{};
		int k = 0;
		System.out.println(new Solution1().winSum(nums, k));
	}

}
