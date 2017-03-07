/**
 * http://www.lintcode.com/zh-cn/problem/permutations/
 * DFS+回溯 根据不重复集合生成全排列
 */
import java.util.List;
import java.util.ArrayList;

/**
 * @author yzwall
 */
class Solution4 {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	
    	// 必须先做异常检测！敲黑板！
    	if(nums == null) {
    		return results;
    	}
    	
    	if(nums.length == 0) {
    		// 输入[]，返回[[]]
    		results.add(new ArrayList<Integer>());
    		return results;
    	}
    	
    	permutationDFS(results, new ArrayList<Integer>(), nums, 0);
    	return results;
    }

    /**
     * DFS+回溯 完成一次全排列搜索
     * @param results 全排列结果
     * @param subset 
     * @param nums
     * @param startIndex 下一个待加入排练的数
     */
	private void permutationDFS(List<List<Integer>> results, 
			                    ArrayList<Integer> subset, 
			                    int[] nums, 
			                    int startIndex) {
		if(subset.size() == nums.length) {
			results.add(new ArrayList<Integer>(subset));
		}
		for(int i = 0; i < nums.length; i++) {
			// 递归出口：所有元素已加入，已生成一个排练
			if(!subset.contains(nums[i])) {
				subset.add(nums[i]);
				
				// (i + 1)%nums.length防止漏掉元素，全排列是无序集合
				permutationDFS(results, subset, nums, (i + 1)%nums.length);
				subset.remove(subset.size() - 1);
			}
		}
	}
}

public class Permutations {

	public static void main(String[] args) {
		
		int[] nums = new int[]{2, 1, 3};
		List<List<Integer>> results = (new Solution4().permute(nums));
		System.out.println(results);

	}

}
