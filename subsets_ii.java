import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/zh-cn/problem/subsets-ii/
 * 通过排序操作保证nums是有序集合，发生重复的条件是当前待选择元素与前一个邻居相等，
 * 且此时前一个邻居元素已经通过回溯被剔除子集(前一个邻居开头的子集已经搜索完毕)
 * @author yzwall
 *
 */
class Solution2 {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
    	ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
    	// 异常检测
		if(nums == null || nums.length == 0) {
			return results;
		}
		// 升序排练，保证DFS的有序性
		Arrays.sort(nums);
    	dfs(new ArrayList<Integer>(), results, nums, 0);
    	
    	return results;
    }

    /**
     * 
     * @param subset
     * @param results
     * @param nums
     * @param startIndex subset末元素在nums中的下一个邻居
     */
	private void dfs(ArrayList<Integer> subset, 
					 ArrayList<ArrayList<Integer>> results, 
					 int[] nums, 
					 int startIndex) {
		// deep copy	
		results.add(new ArrayList<Integer>(subset));
		for(int i = startIndex; i < nums.length; i++) {
			/**
			 * i != 0 防止数组越界
			 * nums[i] == nums[i-1] && i > startIndex 表示以num[i-1]开头的解集
			 * 都已搜索完毕，且num[i-1]通过回溯操作被剔除subset
			 */
			if(i !=0 && nums[i] == nums[i-1] && i > startIndex) {
				// 剪枝操作，跳过num[i]，避免重复
				continue;
			}
			subset.add(nums[i]);
			dfs(subset, results, nums, i + 1);
			subset.remove(subset.size() - 1);
		}
		
	}
}

public class subsets_ii {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{1, 2, 2};
		Solution2 solution = new Solution2();
		ArrayList<ArrayList<Integer>> results = solution.subsetsWithDup(nums);
		System.out.println(results);
	}

}
