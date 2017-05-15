package doublepointers;
import java.util.HashSet;
import java.util.Arrays;

/**
 * ˫ָ���������͵���target�Ĳ��ظ������Ŀ��ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(n)
 * ������֮�͵���target�����в��ظ������Ŀ
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
    		// ���a + b = target, a�ҵ���b��������
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
