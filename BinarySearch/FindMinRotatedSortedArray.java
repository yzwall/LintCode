/**
 * http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array/
 * ���ַ�����ת�������ҳ���СԪ�أ�ʱ�临�Ӷ�o(logn)������ö�ٸ��Ӷ�o(n)
 * �迼����������ѭ��������
 * @author yzwall
 */
package BinarySearch;

class Solution13 {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	int start = 0;
    	int end = nums.length - 1;
    	// ��������û�з�ת
    	if (nums[start] < nums[end]) {
    		return nums[start];
    	}
    	
    	// ���������з�ת����Ԫ�ظ���>2
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		// mid��������
    		if(nums[mid] > nums[start]) {
    			start = mid;
    		} else {
    			// mid��������
    			end = mid;
    			// ���mid�Ƿ�Ϊ��Сֵλ��
    			if(nums[mid] < nums[mid - 1]) {
    				return nums[mid];
    			}
    		}
    	}
    	
    	// ���������з�ת, ��Ԫ�ظ���Ϊ1��2
    	return Math.min(nums[start], nums[end]);
    }
}


public class FindMinRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = new int[]{1};
		System.out.println(new Solution13().findMin(nums));
	}
}
