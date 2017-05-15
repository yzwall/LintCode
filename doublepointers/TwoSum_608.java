package doublepointers;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * �ⷨ1��HashMapʵ�֣�ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)
 * ������֮�ͣ��������ź���
 * http://www.lintcode.com/zh-cn/problem/two-sum-input-array-is-sorted/
 * @author yzwall
 */
class Solution7 {
    public int[] twoSum(int[] nums, int target) {
    	int[] results = new int[2];
    	HashMap<Integer, Integer> map = new HashMap<>();
    	for (int i = 0; i < nums.length; i++) {
    		if (map.containsKey(nums[i])) {
    			results[0] = map.get(nums[i]) + 1;
    			results[1] = i + 1;
    			return results;
    		}
    		map.put(target - nums[i], i);
    	}
    	return results;
    }
}

/**
 * �ⷨ2��˫ָ�뷨��⣬ʱ�丨����O(n)���ռ丨����O(1)
 * ������֮�ͣ��������ź���
 * http://www.lintcode.com/zh-cn/problem/two-sum-input-array-is-sorted/
 * @author yzwall
 */
class Solution8 {
    public int[] twoSum(int[] nums, int target) {
    	int[] results = new int[2];
    	int i = 0;
    	int j = nums.length - 1;
    	while (i < j) {
    		if (nums[i] + nums[j] == target) {
    			results[0] = i + 1;
    			results[1] = j + 1;
    			return results;
    		}
    		if (nums[i] + nums[j] > target) {
    			j--;
    		} else {
    			i++;
    		}
    	}
    	return results;
    }
}



public class TwoSum_608 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
