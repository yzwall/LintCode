package doublepointers;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * 解法1：HashMap求解，时间复杂度O(n^2)，空间复杂度O(n)
 * 求两数之差等于target的组合下标[index1, index2]
 * http://www.lintcode.com/zh-cn/problem/two-sum-difference-equals-to-target/
 * @author yzwall
 */
class Solution18 {
    /*
     * @param nums an array of Integer
     * @param target an integer
     * @return [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
    	if (nums == null || nums.length < 2) {
    		return new int[0];
    	}
    	int[] results = new int[2];
    	HashMap<ArrayList<Integer>, Integer> map = new HashMap<>();
    	for (int i = 0; i < nums.length; i++) {
    		int index = findKey(map, nums[i]);
    		if (index == -1) {
    			ArrayList<Integer> list = new ArrayList<>();
    			list.add(nums[i] + target);
    			list.add(nums[i] - target);
    			map.put(list, i);
    			continue;
    		}
    		results[0] = index + 1;
    		results[1] = i + 1;
    		return results;
    	}
    	
    	return results;
    }
    
    private int findKey(HashMap<ArrayList<Integer>, Integer> map, int num) {
    	for (Map.Entry<ArrayList<Integer>, Integer> entry : map.entrySet()) {
    		ArrayList<Integer> list = entry.getKey();
    		if (list.contains(num)) {
    			return entry.getValue();
    		}
    	}
    	return -1;
    }
}

/**
 * 解法2：暴力求解，时间复杂度O(n^2)，空间复杂度O(1)
 * 求两数之差等于target的组合下标[index1, index2]
 * http://www.lintcode.com/zh-cn/problem/two-sum-difference-equals-to-target/
 * @author yzwall
 */
class Solution19 {
	public int[] twoSum7(int[] nums, int target) {
    	if (nums == null || nums.length < 2) {
    		return new int[0];
    	}
    	int[] results = new int[2];
    	for (int i = 0; i < nums.length; i++) {
    		for (int j = i + 1; j < nums.length; j++) {
    			if (nums[i] - nums[j] == target ||
    				nums[j] - nums[i] == target) {
    				results[0] = i + 1;
    				results[1] = j + 1;
    				return results;
    			}
    		}
    	}
    	return results;
	}
}


public class TwoSum_DifferenceEqualsToTarget_610 {
	public static void main(String[] args) {

	}
}
