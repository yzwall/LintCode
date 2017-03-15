/**
 * http://www.lintcode.com/zh-cn/problem/last-position-of-target/
 * ���ַ�����Ԫ�س������һ��λ�ã������㷨����ģ��
 */
package binarysearch;

/**
 * @author yzwall
 */
class Solution5 {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int lastPosition(int[] nums, int target) {
    	
    	if(nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	int start = 0;
    	int end = nums.length - 1;
    	int pos = Integer.MIN_VALUE;
    	while(start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		// ���ϵ���ȡ���λ��
    		if(nums[mid] == target) {
    			start = mid;
    			pos = Math.max(pos, mid);
    		} 
    		if(nums[mid] < target) {
    			start = mid;
    		}
    		if(nums[mid] > target) {
    			end = mid;
    		}
    	}
    	
    	// ���ȷ���end��Ϊ������λ��
    	if(nums[end] == target) {
    		return Math.max(pos, end);
    	}   
    	
    	if(nums[start] == target) {
    		return Math.max(pos, start);
    	}
	
    	
    	return -1;
    }
}

public class FindLastPosition_458 {

	public static void main(String[] args) {
		
		int[] nums = new int[]{1,2,2,4,5,5};
		System.out.println(new Solution5().lastPosition(nums, 5));
	}

}
