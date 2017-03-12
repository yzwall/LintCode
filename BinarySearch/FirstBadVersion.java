/**
 * 给出序列[0, 0, ... 0, 1, 1, 1...], 二分法找出第1个1出现位置
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
    	
    	// 二分最小区间　A[start] = 0, A[end] = 1
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isBadVersion(mid + 1)) {
    			// 抛弃右侧1区域，逼近first
    			end = mid;
    		} else {
    			// 抛弃左侧0区域,逼近first
    			start = mid;
    		}
    	}
    	
        // 序列全1情况下，A[start] = 0
    	if (isBadVersion(start + 1)) {
    		return start + 1;
    	}
    	
    	// 返回第一个1出现位置
    	return end + 1;
    }
    
	// 添加测试变量，模拟代码版本号序列
	private static int[] nums;
	
	/**
	 * 添加测试方法
	 * 判断代码版本号序列中第k版本是否错误
	 */
	public static boolean isBadVersion(int k) {
		if(k - 1 < 0 || nums == null || nums.length == 0) {
			return false;
		}
		return nums[k-1] == 1 ? true : false;
	}
	
	/**
	 * 添加测试方法，生成代码版本号序列
	 * @param n 代码库序列长度
	 * @param first	第一个出错的版本号
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
