/**
 * http://www.lintcode.com/zh-cn/problem/permutations-ii/
 * https://leetcode.com/problems/permutations-ii/?tab=Description
 * DFS+����+��֦�������ظ�Ԫ�ص�ȫ����
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yzwall
 */
class Solution6 {

    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	
    	// �쳣���
    	if(nums == null) {
    		return results;
    	}
    	
    	if(nums.length == 0) {
    		results.add(new ArrayList<Integer>());
    		return results;
    	}
    	
    	Arrays.sort(nums);
    	
    	// visit�������Ԫ���Ƿ��Ѽ�����
    	boolean[] visit = new boolean[nums.length];
    	
    	helper(results, new ArrayList<Integer>(), nums, visit);
    	return results;
    }

    /**
     * ��֦������DFS��·����ʱ����˼�֦�������ݽṹ���·����ڻ��ݲ���֮����һ�����
     * @param results ���ȫ���н��
     * @param subset �����������
     * @param nums
     * @param visit 
     */
	private void helper(List<List<Integer>> results, 
						ArrayList<Integer> subset, 
						int[] nums, 
						boolean[] visit) {
		// DFS�ݹ����
		if (subset.size() == nums.length) {
			results.add(new ArrayList<Integer>(subset));
			return;
		}
		
		/**
		 * ��֦������pre��ʾ��һ����num[i]ͬ����·�����ѱ������޳������ڼ�֦ȥ��
		 * ���÷�Χ�����һ�����ѣ���˲��ɶ����ȫ�ֱ���
		 */
		long pre = Long.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			// ͬ��·��ֻȡһ�Σ���ֹ�ظ�
			if (visit[i] || pre == nums[i]) {
				continue;
			}
			
			subset.add(nums[i]);
			visit[i] = true;
			helper(results, subset, nums, visit);
			// pre ��ʾ�����ݱ��޳��Ӽ�Ԫ��
			pre = subset.get(subset.size() - 1);
			subset.remove(subset.size() - 1);
			visit[i] = false;	
		}
	} 
}

public class PermutationsIIPre {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{3,3,0,3};
		List<List<Integer>> results = (new Solution6().permuteUnique(nums));
		System.out.println(results);
	}

}
