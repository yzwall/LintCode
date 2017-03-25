/**
 * �������ϣ��ҳ����к͵���target�����, Ԫ�ز����ظ�ѡ����ϲ����ظ�ѡ��
 * ����Ԥ����+DFS+�������
 * http://www.lintcode.com/en/problem/combination-sum-ii/
 * @author yzwall
 */
package dfs;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution10 {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	if (num == null || num.length == 0) {
    		return results;
    	}
    	
    	Arrays.sort(num);
    	helper(results, new ArrayList<Integer>(), num, 0, target);
    	return results;
    }

	private void helper(List<List<Integer>> results, 
						ArrayList<Integer> subset, 
						int[] num, 
						int startIndex,
						int target) {
		// �����Ϸ�����
		if (target == 0 && !results.contains(subset)) {
			results.add(new ArrayList<Integer>(subset));
			return;
		}

		// ���Ԫ��֮�ͳ���ԭtarget����Ҫ����
		if (target < 0) {
			return;
		} 
		
		for (int i = startIndex; i < num.length; i++) {
			if (num[i] <= target) {
				subset.add(num[i]);
				helper(results, subset, num, i + 1, target - num[i]);
				subset.remove(subset.size() - 1);
			}
		}
	}
}

public class CombinationSumII_153 {

	public static void main(String[] args) {
		int[] candidates = new int[]{10, 1, 6, 7, 2, 1, 5};
		int target = 8;
		System.out.println(new Solution10().combinationSum2(candidates, target));
	}
}
