/**
 * www.lintcode.com/zh-cn/problem/classical-binary-search/
 * ���ֲ���Ԫ�ص�һ�γ���λ�ã�O(logn)���Ӷ�, �����㷨���ֲ���ģ��
 */

package binarysearch;

/**
 * @author yzwall
 */
class Solution4 {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int binarySearch(int[] nums, int target) {
    	if(nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	// ������ֲ���ģ��, ����Ԥ����������
    	int start = 0;
    	int end = nums.length - 1;
    	int pos = Integer.MAX_VALUE;
    	while (start + 1 < end) {
    		
    		// ����Ϊ�����nums[start, mid]���ҷ���nums[mid+1, end]
    		int mid = start + (end - start) / 2;
    		
    		if (nums[mid] == target) {
    			// ��ʱ��¼ƥ��λ�ã����ϵ�����Сֵ
    			pos = Math.min(pos, mid);
    			end = mid;
    		}
    		
    		if (nums[mid] < target) {
    			start = mid;
    		}
    		
    		if (nums[mid] > target) {
    			end = mid;
    		}
    	}
    	
    	// �������ȳ���λ��
		if(nums[start] == target) {
			return Math.min(pos, start);
		}
		if(nums[end] == target) {
			return Math.min(pos, end);
		}
    	return -1;
    }
}

public class FirstPosition_14 {

	public static void main(String[] args) {
		
		int[] nums = new int[]{8,8,8,8,8,8,8,10,13,14};
		System.out.println(new Solution4().binarySearch(nums, 8));

	}

}
