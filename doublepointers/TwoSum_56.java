package doublepointers;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * �ⷨ1:ʱ�临�Ӷ�O(n^2)���ռ临�Ӷ�O(1)
 * ����������֮�͵���target�����������±꣨��1��ʼ��
 * http://www.lintcode.com/zh-cn/problem/two-sum/
 * @author yzwall
 */
class Solution5 {
	public int[] twoSum(int[] nums, int target) {
		int[] results = new int[2];
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					results[0] = i + 1;
					results[1] = j + 1;
					return results;
				}
			}
		}
		return results;
	}
}

/**
 * �ⷨ2:HashMap��⣬ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)
 * ����������֮�͵���target�����������±꣨��1��ʼ��
 * http://www.lintcode.com/zh-cn/problem/two-sum/
 * @author yzwall
 */
class Solution6 {
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] results = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				results[0] = map.get(nums[i]) + 1;
				results[1] = i + 1;
				break;
			}
			map.put(target - nums[i], i);
		}
		return results;
	}
}

/**
 * �ⷨ3:HashMap + ˫ָ����⣬ʱ�临�Ӷ�O(nlogn)���ռ临�Ӷ�O(n)
 * ����������֮�͵���target�����������±꣨��1��ʼ��
 * http://www.lintcode.com/zh-cn/problem/two-sum/
 * @author yzwall
 */
class Solution {
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		int[] results = new int[2];
		// HashMap���ڼ�¼����ǰ����Ԫ�ض�Ӧ�±�
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.get(nums[i]).add(i);
				continue;
			}
			map.put(nums[i], new ArrayList<Integer>());
			map.get(nums[i]).add(i);
		}
        
		int i = 0, j = nums.length - 1;
		// �����˫ָ�����
		Arrays.sort(nums);
		while (i < j) {
			if (nums[i] + nums[j] == target) {
			    int index1 = map.get(nums[i]).get(0);
			    // �ظ�Ԫ���Ѿ����ʹ�һ�Σ��Ӷ�Ӧλ���б����޳�
			    map.get(nums[i]).remove(0);
			    int index2 = map.get(nums[j]).get(0);
			    // ��֤results[0] < result[1]
			    results[0] = Math.min(index1, index2) + 1;
			    results[1] = Math.max(index1, index2) + 1;
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


public class TwoSum_56 {
	public static void main(String[] args) {

	}
}
