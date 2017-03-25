/**
 * �ڸ��������У��ҵ�������֮��Ϊ0��������ϣ�Ҫ�󲻵��ظ�ѡ��Ԫ�أ������ظ�ѡ�����
 * http://www.lintcode.com/en/problem/3sum/
 * @author yzwall
 */
package dfs;
import java.util.ArrayList;
import java.util.Arrays;

class Solution9 {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
    	ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
    	if (numbers == null || numbers.length == 0) {
    		results.add(new ArrayList<Integer>());
    		return results;
    	}
    	
    	Arrays.sort(numbers);
    	helper(results, new ArrayList<Integer>(), numbers, 0, 0, 0);
    	return results;
    }

	private void helper(ArrayList<ArrayList<Integer>> results,
						ArrayList<Integer> subset,
						int[] numbers, 
						int sum,
						int startIndex, 
						int target) {
		// �������ڣ��Ϸ����
		if (sum == target && subset.size() == 3 && 
			!results.contains(subset)) {
			
			results.add(new ArrayList<Integer>(subset));
			return;
		}
		// �������ڣ���ЧҶ�ӽڵ�
		if (subset.size() == 3) {
			return;
		}
		
		for (int i = startIndex; i < numbers.length; i++) {
			subset.add(numbers[i]);
			sum += numbers[i];
			// ��ϲ����ظ���i + 1
			helper(results, subset, numbers, sum, i + 1, target);
			sum -= numbers[i];
			subset.remove(subset.size() - 1);
		}
	}
}



public class Sum3_57 {

	public static void main(String[] args) {
		int[] numbers = new int[]{-1, 0, 1, 2, -1, -4};
		System.out.println(new Solution9().threeSum(numbers));
	}

}
