/**
 * www.lintcode.com/zh-cn/problem/classical-binary-search/
 * ������ֲ��ң�O(logn)���Ӷ�, �������ְ汾
 */

package BinarySearch;

class Solution3 {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int findPosition(int[] nums, int target) {
    	if(nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	// ������ֲ���ģ��, ����Ԥ����������
    	int start = 0;
    	int end = nums.length - 1;
    	while (start < end) {
    		// ����Ϊ�����nums[start, mid]���ҷ���nums[mid+1, end]
    		int mid = start + (end - start) / 2;
    		
    		if (nums[mid] == target) {
    			return mid;
    		}
    		
    		// target���ҷ����У�����start
    		if (nums[mid] < target) {
    			start = mid + 1;
    		}
    		
    		// target��������У�����end
    		if (nums[mid] > target) {
    			end = mid - 1;
    		}
    		
    		// ����start��end���������
    		if(nums[start] == target) {
    			return start;
    		}
    		if(nums[end] == target) {
    			return end;
    		}
    	}
    	return -1;
    	
    }
}

public class FindPosition {

	public static void main(String[] args) {
		
		int[] nums = new int[]{1, 2, 3, 4, 5, 6};
		System.out.println(new Solution3().findPosition(nums, 6));

	}

}
