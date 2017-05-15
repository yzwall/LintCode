package doublepointers;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;

/**
 * �ⷨ1��ʱ�临�Ӷ�O(n^2)���ռ临�Ӷ�O(1) 
 * ������֮�ʹ���target�������Ŀ
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
 * �ⷨ2��˫ָ�뷨��⣬ʱ�临�Ӷ�O(logn)���ռ临�Ӷ�O(1) 
 * ������֮�ʹ���target�������Ŀ
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
	    			// k ~ (i, j)���������ض�����nums[k] + nums[j] > target
	    			pairs += j - i;
	    			// nums[j]���з��������ϣ�j--
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
