/**
 * http://www.lintcode.com/zh-cn/problem/total-occurrence-of-target/
 * 二分法找出第一次和最后一次target出现位置，作差得出出现次数总和
 * @author yzwall
 */
package binarysearch;

class Solution7 {
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int totalOccurrence(int[] A, int target) {
    	// 异常检查
    	if(A == null || 0 == A.length) {
    		return 0;
    	}
    	
    	int start = 0;
    	int end = A.length - 1;
    	
    	// target第一次出现位置
    	int left;
    	// target最后一次出现位置
    	int right;
    	
    	// 二分查找left时，第一次遇到target时的位置
    	int firstMeet = 0;
    	
    	// [0, A.lengt - 1]，二分查找left
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (A[mid] >= target) {
    			// 仅当第一次遇到target时记录坐标
    			if (firstMeet == 0 && A[mid] == target) {
    				firstMeet = mid;
    			}
    			// 抛弃右分量，逼近left
    			end = mid;
    		} else {
    			// 抛弃左分量，逼近left
    			start = mid;
    		}
    	}
    	
    	// 优先检查二分最小区间左边界，确保left
    	if (A[start]  == target) {
    		left = start;
    	} else if(A[end] == target) {
    		left = end;
    	} else {
    		// 不存在target
    		return 0;
    	}
    	
    	// 更新start，避免TLE
    	start = firstMeet;
    	end = A.length - 1;
    	// [firstMeet, A.length - 1]二分查找right
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (A[mid] <= target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	// 优先检查二分最小区间右边界，确保right
    	right = (A[end]  == target ? end : start);
    	
    	return right - left + 1;
    	
    }
}

//	分治法在大数据量下，n过大导致递归TLE
//	private int binarySearchCount(int[] A, int start, int end, int target) {
//		int count = 0;
//		
//		// 递归出口
//		if(!(start + 1 < end)) {
//			if (A[start] == target) {
//				count++;
//			} 
//			if (A[end] == target && end == A.length - 1) {
//				count++;
//			}
//			return count;
//		}
//
//		// 分治
//		int mid = start + (end - start) / 2;
//		if (A[mid] == target) {
//			count += binarySearchCount(A, start, mid, target); 
//			count += binarySearchCount(A, mid, end, target);
//		} else if(A[mid] < target) {
//			count +=  binarySearchCount(A, mid, end, target); 
//		} else {
//			count +=  binarySearchCount(A, start, mid, target);
//		}
//		
//		// 合并
//		return count;
//	}
	
//    public int totalOccurrence(int[] A, int target) {
//        // Write your code here
//        int n = A.length;
//        if (n == 0)
//            return 0;
//        if (A[n-1] < target || A[0] > target)
//            return 0;
//        
//        int l = 0, r = n - 1;
//        int start = 0;
//        while (l <= r) {
//            int mid = (l + r) >> 1;
//            if (A[mid] >= target) {
//                start = mid;
//                r = mid - 1;
//            } else
//                l = mid + 1;
//        }
//        if (A[start] != target)
//            return 0;
//
//        int end = n-1;
//        l = 0; r = n-1;
//        while (l <= r) {
//            int mid = (l + r) >> 1;
//            if (A[mid] <= target) {
//                end = mid;
//                l = mid + 1;
//            } else
//                r = mid - 1;
//        }
//        return end - start + 1;
//    }

public class TotalOccurrence_462 {

	public static void main(String[] args) {
		
		int[] A = new int[]{1, 2, 2, 2, 2, 2};
		Solution7 testCase = new Solution7();
		System.out.println(testCase.totalOccurrence(A, 2));
	}

}
