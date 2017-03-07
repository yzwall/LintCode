/**
 * http://www.lintcode.com/zh-cn/problem/permutations-ii/
 * https://leetcode.com/problems/permutations-ii/?tab=Description
 * DFS+回溯+剪枝，生成重复元素的全排列
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
    	
    	// 异常检测
    	if(nums == null) {
    		return results;
    	}
    	
    	if(nums.length == 0) {
    		results.add(new ArrayList<Integer>());
    		return results;
    	}
    	
    	Arrays.sort(nums);
    	
    	// visit标记数组元素是否已加入结果
    	boolean[] visit = new boolean[nums.length];
    	
    	helper(results, new ArrayList<Integer>(), nums, visit);
    	return results;
    }

    /**
     * 剪枝发生在DFS换路搜索时，因此剪枝所需数据结构更新发生在回溯操作之后下一条语句
     * @param results 存放全排列结果
     * @param subset 排练结果集合
     * @param nums
     * @param visit 
     */
	private void helper(List<List<Integer>> results, 
						ArrayList<Integer> subset, 
						int[] nums, 
						boolean[] visit) {
		// DFS递归出口
		if (subset.size() == nums.length) {
			results.add(new ArrayList<Integer>(subset));
			return;
		}
		
		/**
		 * 剪枝变量：pre表示上一次与num[i]同级的路径，已被回溯剔除，用于剪枝去重
		 * 作用范围在完成一次深搜，因此不可定义成全局变量
		 */
		long pre = Long.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			// 同级路径只取一次，防止重复
			if (visit[i] || pre == nums[i]) {
				continue;
			}
			
			subset.add(nums[i]);
			visit[i] = true;
			helper(results, subset, nums, visit);
			// pre 表示待回溯被剔除子集元素
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
