/**
 * http://www.lintcode.com/zh-cn/problem/maximum-number-in-mountain-sequence/
 * @author yzwall
 */

package BinarySearch;

class Solution11 {
	/**
	 * @param nums a mountain sequence which increase firstly and then decrease
	 * @return then mountain top
	 */
	public int mountainSequence(int[] nums) {
		
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int start = 0;
		int end = nums.length - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (getZone(nums, mid) == 0) {
				return nums[mid];
			} 
			// mid�����������������������
			if(getZone(nums, mid) == -1) {
				start = mid;
			} else {
				// mid���½����������ұ��½���
				end = mid;
			}
		}
		
		// mauntain sequenceΪ�����ݼ����У���ֵΪ��Ԫ��
		return nums[0];
		
	}
	
	/**
	 * �ж�nums[index]�����ĸ�����
	 * ���ַ��ϸ�֤index����Խ��
	 * @param index Ԫ��λ��
	 * @return 0 ��ֵ
	 * 		   -1 λ��������
	 		   1 λ���½���
	 */
	private int getZone(int[] nums, int index) {
		// �ҵ���ֵ
		if (nums[index - 1] < nums[index] && 
		    nums[index + 1] < nums[index]) {
			return 0;
		}
		// nums[index]��������
		if (nums[index - 1] < nums[index] && 
		    nums[index + 1] > nums[index]) {
			return -1;
		} else {
			return 1;
		}
	}
}

public class MountainSequence {

	public static void main(String[] args) {
		int[] nums = new int[]{10, 9, 8, 7};
		System.out.println(new Solution11().mountainSequence(nums));
	}

}
