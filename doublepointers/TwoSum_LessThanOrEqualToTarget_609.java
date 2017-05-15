package doublepointers;
import java.util.Arrays;

/**
 * ˫ָ�뷨��⣬ʱ�临�Ӷ�O(nlogn)���ռ临�Ӷ�O(1)
 * ������֮��С�ڵ���target�����������Ŀ
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
    		// nums[i]��������� = j - i
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
