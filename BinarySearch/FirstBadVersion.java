/**
 * ��������[0, 0, ... 0, 1, 1, 1...], ���ַ��ҳ���1��1����λ��
 * http://www.lintcode.com/zh-cn/problem/maximum-number-in-mountain-sequence/
 * @author yzwall
 */
package BinarySearch;

class Solution9 {
    /**
     * @param n: An integers.
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
    	if (isBadVersion(1)) {
    		return 1;
    	}
    	
    	int start = 0;
    	int end = nums.length - 1;
    	
    	// ������С���䡡A[start] = 0, A[end] = 1
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isBadVersion(mid + 1)) {
    			// �����Ҳ�1���򣬱ƽ�first
    			end = mid;
    		} else {
    			// �������0����,�ƽ�first
    			start = mid;
    		}
    	}
    	
        // ����ȫ1����£�A[start] = 0
    	if (isBadVersion(start + 1)) {
    		return start + 1;
    	}
    	
    	// ���ص�һ��1����λ��
    	return end + 1;
    }
    
	// ��Ӳ��Ա�����ģ�����汾������
	private static int[] nums;
	
	/**
	 * ��Ӳ��Է���
	 * �жϴ���汾�������е�k�汾�Ƿ����
	 */
	public static boolean isBadVersion(int k) {
		if(k - 1 < 0 || nums == null || nums.length == 0) {
			return false;
		}
		return nums[k-1] == 1 ? true : false;
	}
	
	/**
	 * ��Ӳ��Է��������ɴ���汾������
	 * @param n ��������г���
	 * @param first	��һ������İ汾��
	 */
	public Solution9(int n, int first) {
		nums = new int[n];
		for(int i = 0; i < first - 1; i++) {
			nums[i] = 0;
		}
		for(int j = first - 1; j < n; j++) {
			nums[j] = 1;
		}
		for(int num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
}


public class FirstBadVersion {
	
	public static void main(String[] args) {
		int n = 10;
		Solution9 test = new Solution9(n, 10);
		System.out.println("first: " + test.findFirstBadVersion(n));
	}
}
