/**
 * DFS+����
 * ��HashMapȥ���ҳ��������ϵ����в��ظ��Ӽ������Ӽ��ڲ����ظ�Ԫ��
 */
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author yzwall
 */
class Solutionii {
	/**
	 * 
	 * @param nums ������
	 * @return �������ظ��Ӽ�
	 */
	public HashSet<HashSet<Integer>> subsetsWithDup(int[] nums) {
		
		HashSet<HashSet<Integer>> results = new HashSet<HashSet<Integer>>();
		// �쳣���
		if(nums == null || nums.length == 0) {
			return results;
		}
		Arrays.sort(nums);
		dfs(new HashSet<Integer>(), results, nums, 0);
		return results;
	}

	/**
	 * 
	 * @param hashSet ��startIndex��ͷ�����ظ�Ԫ���Ӽ�
	 * @param results
	 * @param startIndex
	 */
	private void dfs(HashSet<Integer> subsets, HashSet<HashSet<Integer>> results, int[] nums, int startIndex) {
		// �ݹ�ֽ�
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
