import java.util.ArrayList;
import java.util.Arrays;

/**
 * http://www.lintcode.com/zh-cn/problem/subsets-ii/
 * ͨ�����������֤nums�����򼯺ϣ������ظ��������ǵ�ǰ��ѡ��Ԫ����ǰһ���ھ���ȣ�
 * �Ҵ�ʱǰһ���ھ�Ԫ���Ѿ�ͨ�����ݱ��޳��Ӽ�(ǰһ���ھӿ�ͷ���Ӽ��Ѿ��������)
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
    	// �쳣���
		if(nums == null || nums.length == 0) {
			return results;
		}
		// ������������֤DFS��������
		Arrays.sort(nums);
    	dfs(new ArrayList<Integer>(), results, nums, 0);
    	
    	return results;
    }

    /**
     * 
     * @param subset
     * @param results
     * @param nums
     * @param startIndex subsetĩԪ����nums�е���һ���ھ�
     */
	private void dfs(ArrayList<Integer> subset, 
					 ArrayList<ArrayList<Integer>> results, 
					 int[] nums, 
					 int startIndex) {
		// deep copy	
		results.add(new ArrayList<Integer>(subset));
		for(int i = startIndex; i < nums.length; i++) {
			/**
			 * i != 0 ��ֹ����Խ��
			 * nums[i] == nums[i-1] && i > startIndex ��ʾ��num[i-1]��ͷ�Ľ⼯
			 * ����������ϣ���num[i-1]ͨ�����ݲ������޳�subset
			 */
			if(i !=0 && nums[i] == nums[i-1] && i > startIndex) {
				// ��֦����������num[i]�������ظ�
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
