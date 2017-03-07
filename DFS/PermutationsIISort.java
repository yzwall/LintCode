package DFS;
/**
 * ��Ŀ����
 * DFS+����+��֦�������ظ�Ԫ�ص�ȫ����
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yzwall
 */
class Solution5 {

	// visit�������Ԫ���Ƿ��Ѽ�����
	public boolean[] visit;
	
    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	
    	// �쳣���
    	if(nums == null) {
    		return results;
    	}
    	
    	if(nums.length == 0) {
    		results.add(new ArrayList<Integer>());
    		return results;
    	}
    	
    	// ͨ�����򣬹涨�ظ�Ԫ���ڽ���е�˳��������е�˳�����һ��
    	Arrays.sort(nums);
    	
    	visit = new boolean[nums.length];
    	
    	helper(results, new ArrayList<Integer>(), nums);
    	return results;
    }

    /**
     * @param results ���ȫ���н��
     * @param subset �����������
     * @param nums
     */
	private void helper(List<List<Integer>> results, 
						ArrayList<Integer> subset, 
						int[] nums) {
		// DFS�ݹ����
		if(subset.size() == nums.length) {
			results.add(new ArrayList<Integer>(subset));
			return;
		}
		
		for(int i = 0; i < nums.length; i++) {
			/**
			 * ͨ������������������涨�ظ�Ԫ���ڽ���е�˳��������е�˳�����һ�£�ʵ�ּ�֦ȥ��
			 * i != 0 ���ڴ���nums[i-1]����ֹ����Խ��
			 * visit[i]Ϊ�棬��ʾ��ǰnums[i]�Ѽ������У� pass��nums[i]
			 * nums[i-1] == nums[i] && !visit[i-1] ��ʾ��nums[i]�ظ���������֮ǰ��Ԫ��nums[i-1]û�м�����, ����nums[i]Υ��ȥ�ع涨��pass��nums[i]
			 */
			if(visit[i] || (i != 0 
							&& nums[i-1] == nums[i]
							&& !visit[i-1])) {
				continue; 
			}
			subset.add(nums[i]);
			visit[i] = true;
			helper(results, subset, nums);
			subset.remove(subset.size() - 1);
			visit[i] = false;
		}
	} 
}

public class PermutationsIISort {

	public static void main(String[] args) {
		
		int[] nums = new int[]{1, 2, 2};
		List<List<Integer>> results = (new Solution5().permuteUnique(nums));
		System.out.println(results);
	}

}
