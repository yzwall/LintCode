/**
 * 给定集合，找出所有和等于target的组合, 元素可重复选择，组合不可重复选择
 * 排序预处理+DFS+回溯求解
 * @author yzwall
 */
package dfs;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution8 {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (candidates == null || candidates.length == 0) {
			return results;
		}
		Arrays.sort(candidates);
		if (candidates[0] > target) {
			results.add(new ArrayList<Integer>());
			return results;
		}
		
		helper(results, candidates, new ArrayList<Integer>(), 0, target);
		return results;
	}

	private void helper(List<List<Integer>> results, 
						int[] candidates,
						ArrayList<Integer> subSet,
						int startIndex,
						int target) {
		// 搜索到组合 去重添加
		if (target == 0 && !results.contains(subSet)) {
			results.add(new ArrayList<Integer>(subSet));
			return;
		}
		
		// 搜索出口
		if (target < 0) {
			return;
		}
		
		for (int i = startIndex; i < candidates.length; i++) {
			if (candidates[i] <= target) {
				subSet.add(candidates[i]);
				helper(results, candidates, subSet, i, target - candidates[i]);
				subSet.remove(subSet.size() - 1);
			}
		}
	}
}

public class CombinationSum_135 {
	public static void main(String[] args) {
		int[] candidates = new int[]{2, 3, 6, 7};
		int target = 7;
		
		System.out.println(new Solution8().combinationSum(candidates, target));
	}
}
