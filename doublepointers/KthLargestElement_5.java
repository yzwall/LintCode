package doublepointers;

import java.util.Arrays;

/**
 * �ⷨ1�����������з�˼�����K��Ԫ�أ�ʱ�临�Ӷ�O(n)���ռ临�Ӷ�O(1)
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element/
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element-ii/
 * @author yzwall
 */
class Solution1 {
    public int kthLargestElement(int k, int[] nums) {
    	if (nums == null) {
    		return -1;
    	}
    	return quickSelect(nums, 0, nums.length - 1, k);
    } 
    /**
     * ���������з�˼�����k��Ԫ�أ�ƽ��ʱ�临�Ӷ�O(n)
     */
    private int quickSelect(int[] nums, int start, int end, int k) {
		if (start == end) {
			return nums[start];
		}
		
		int leftIndex = start;
		int rightIndex = end;
		// �з־�������
		int pivot = nums[start + (end - start) / 2];
		while (leftIndex <= rightIndex) {
			while (leftIndex <= rightIndex && nums[leftIndex] > pivot) {
				leftIndex++;
			}
			while (leftIndex <= rightIndex && nums[rightIndex] < pivot) {
				rightIndex--;
			}
			if (leftIndex <= rightIndex) {
				int temp = nums[leftIndex]; 
				nums[leftIndex] = nums[rightIndex];
				nums[rightIndex] = temp;
				leftIndex++;
				rightIndex--;
			}
		}
		
		// ��K��Ԫ����pivot��ߣ� ��С�����ģ
		if (start + k - 1 <= rightIndex) {
			return quickSelect(nums, start, rightIndex, k);
		}
		// ��K��Ԫ����pivot�ұߣ� ��С�����ģ
		if (start + k - 1 >= leftIndex) {
			return quickSelect(nums, leftIndex, end, k - (leftIndex - start));
		}
		// ��K��Ԫ����nums[rightIndex]~nums[leftIndex]֮��
    	return nums[leftIndex - 1];
    }
}

/**
 * �ⷨ2����ͳһ����ʱ�临�Ӷ�O(nlogn)���ռ临�Ӷ�O(1)
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element/
 * http://www.lintcode.com/zh-cn/problem/kth-largest-element-ii/
 * @author yzwall
 */
class Solution2 {
    public int kthLargestElement(int k, int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
};

public class KthLargestElement_5 {
	public static void main(String[] args) {
		int[] nums = new int[]{3,44,38,5,47,15,26,26,27,2,46,4,19,50,48};
		System.out.println(new Solution1().kthLargestElement(3, nums));
	}
}
