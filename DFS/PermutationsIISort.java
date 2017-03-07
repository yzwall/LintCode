package DFS;
/**
 * 题目链接
 * DFS+回溯+剪枝，生成重复元素的全排列
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yzwall
 */
class Solution5 {

	// visit标记数组元素是否已加入结果
	public boolean[] visit;
	
    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	
    	// 异常检测
    	if(nums == null) {
    		return results;
    	}
    	
    	if(nums.length == 0) {
    		results.add(new ArrayList<Integer>());
    		return results;
    	}
    	
    	// 通过排序，规定重复元素在结果中的顺序和数组中的顺序必须一致
    	Arrays.sort(nums);
    	
    	visit = new boolean[nums.length];
    	
    	helper(results, new ArrayList<Integer>(), nums);
    	return results;
    }

    /**
     * @param results 存放全排列结果
     * @param subset 排练结果集合
     * @param nums
     */
	private void helper(List<List<Integer>> results, 
						ArrayList<Integer> subset, 
						int[] nums) {
		// DFS递归出口
		if(subset.size() == nums.length) {
			results.add(new ArrayList<Integer>(subset));
			return;
		}
		
		for(int i = 0; i < nums.length; i++) {
			/**
			 * 通过对数组排序操作，规定重复元素在结果中的顺序和数组中的顺序必须一致，实现剪枝去重
			 * i != 0 由于存在nums[i-1]，防止数组越界
			 * visit[i]为真，表示当前nums[i]已加入排列， pass掉nums[i]
			 * nums[i-1] == nums[i] && !visit[i-1] 表示与nums[i]重复且排在其之前的元素nums[i-1]没有加入结果, 加入nums[i]违反去重规定，pass掉nums[i]
			 */
			if(visit[i] || (i != 0 
							&& nums[i-1] == nums[i]
							&& !visit[i-1])) {
				continue; 
			}
			subset.add(nums[i]);
			visit[i] = true;
			helper(results, subset, nums);
			subset.remove(subset.size() - 1);
			visit[i] = false;
		}
	} 
}

public class PermutationsIISort {

	public static void main(String[] args) {
		
		int[] nums = new int[]{1, 2, 2};
		List<List<Integer>> results = (new Solution5().permuteUnique(nums));
		System.out.println(results);
	}

}
