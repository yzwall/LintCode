package dfs;
/**
 * DFS+回溯 
 * http://www.lintcode.com/problem/subsets/
 * @author yzwall
 */
import java.util.ArrayList;
import java.util.Arrays;

class Solution1 {
    /**
     * @param nums A set of numbers.
     * @return results A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        
        // 检查边界条件和异常错误
        if(nums == null || nums.length == 0) {
            return results;
        }
        
        // 升序输入集合
        Arrays.sort(nums);
        
        // 找到所有以空集开头的子集，加入results
        getSubsets(new ArrayList<Integer>(), results, nums, 0);
        
        return results;
    }
    
    /**
     * 找到所有以subset开头的子集，加入results
     * @param subset 以startIndex开始的升序子集
     */
    private void getSubsets(ArrayList<Integer> subset,
                            ArrayList<ArrayList<Integer>> results,
                            int[] nums,
                            int startIndex) {
        // 放入自身
        results.add(new ArrayList<Integer>(subset));
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            // 执行深搜，放入以i+1开始的子集
            getSubsets(subset, results, nums, i + 1);
            // 回溯操作
            subset.remove(subset.size() - 1);
        }
        // 递归出口（什么时候不往下递归，直接找到答案）
    }
}

public class SubSets_17 {
	public static void main(String[] args) {
		Solution1 ss = new Solution1();
		int[] nums = new int[]{1,2,2};
		ArrayList<ArrayList<Integer>> results = ss.subsets(nums);
		System.out.println(results.toString());
	}
}