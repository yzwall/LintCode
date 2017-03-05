/**
 * http://www.lintcode.com/problem/subsets/
 * DFS+����
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yzwall
 */
class Solution {
    /**
     * @param nums A set of numbers.
     * @return results A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        
        // ���߽��������쳣����
        if(nums == null || nums.length == 0) {
            return results;
        }
        
        // �������뼯��
        Arrays.sort(nums);
        
        // �ҵ������Կռ���ͷ���Ӽ�������results
        getSubsets(new ArrayList<Integer>(), results, nums, 0);
        
        return results;
    }
    
    /**
     * �ҵ�������subset��ͷ���Ӽ�������results
     * @param subset ��startIndex��ʼ�������Ӽ�
     */
    private void getSubsets(ArrayList<Integer> subset,
                                ArrayList<ArrayList<Integer>> results,
                                int[] nums,
                                int startIndex) {
        // ��������
        results.add(new ArrayList<Integer>(subset));
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            // ִ�����ѣ�������i+1��ʼ���Ӽ�
            getSubsets(subset, results, nums, i + 1);
            // ���ݲ���
            subset.remove(subset.size() - 1);
        }
        // �ݹ���ڣ�ʲôʱ�����µݹ飬ֱ���ҵ��𰸣�
    }
}

public class subsets {
	public static void main(String[] args) {
		Solution ss = new Solution();
		int[] nums = new int[]{1,2,3};
		ArrayList<ArrayList<Integer>> results = ss.subsets(nums);
		System.out.println(results.toString());
	}
}

