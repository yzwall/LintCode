/**
 * DFS+回溯
 * 用HashMap去重找出给定集合的所有不重复子集，且子集内部无重复元素
 */
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author yzwall
 */
class Solutionii {
	/**
	 * 
	 * @param nums 父集合
	 * @return 所有无重复子集
	 */
	public HashSet<HashSet<Integer>> subsetsWithDup(int[] nums) {
		
		HashSet<HashSet<Integer>> results = new HashSet<HashSet<Integer>>();
		// 异常检测
		if(nums == null || nums.length == 0) {
			return results;
		}
		Arrays.sort(nums);
		dfs(new HashSet<Integer>(), results, nums, 0);
		return results;
	}

	/**
	 * 
	 * @param hashSet 以startIndex开头的无重复元素子集
	 * @param results
	 * @param startIndex
	 */
	private void dfs(HashSet<Integer> subsets, HashSet<HashSet<Integer>> results, int[] nums, int startIndex) {
		// 递归分解
		results.add(new HashSet<Integer>(subsets));
		
		for(int i = startIndex; i < nums.length; i++) {
			subsets.add(nums[i]);
			dfs(subsets, results, nums, i + 1);
			subsets.remove(nums[i]);
		}
	}
	
}


public class SubsetsIIHashset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{1, 2, 2, 1};
		Solutionii ss = new Solutionii();
		HashSet<HashSet<Integer>> results = ss.subsetsWithDup(nums);
		System.out.println(results);
	}

}
