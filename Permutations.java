/**
 * http://www.lintcode.com/zh-cn/problem/permutations/
 * DFS+���� ���ݲ��ظ���������ȫ����
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
    	
    	// ���������쳣��⣡�úڰ壡
    	if(nums == null) {
    		return results;
    	}
    	
    	if(nums.length == 0) {
    		// ����[]������[[]]
    		results.add(new ArrayList<Integer>());
    		return results;
    	}
    	
    	permutationDFS(results, new ArrayList<Integer>(), nums, 0);
    	return results;
    }

    /**
     * DFS+���� ���һ��ȫ��������
     * @param results ȫ���н��
     * @param subset 
     * @param nums
     * @param startIndex ��һ����������������
     */
	private void permutationDFS(List<List<Integer>> results, 
			                    ArrayList<Integer> subset, 
			                    int[] nums, 
			                    int startIndex) {
		if(subset.size() == nums.length) {
			results.add(new ArrayList<Integer>(subset));
		}
		for(int i = 0; i < nums.length; i++) {
			// �ݹ���ڣ�����Ԫ���Ѽ��룬������һ������
			if(!subset.contains(nums[i])) {
				subset.add(nums[i]);
				
				// (i + 1)%nums.length��ֹ©��Ԫ�أ�ȫ���������򼯺�
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
