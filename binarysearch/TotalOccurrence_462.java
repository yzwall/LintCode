/**
 * http://www.lintcode.com/zh-cn/problem/total-occurrence-of-target/
 * ���ַ��ҳ���һ�κ����һ��target����λ�ã�����ó����ִ����ܺ�
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
    	// �쳣���
    	if(A == null || 0 == A.length) {
    		return 0;
    	}
    	
    	int start = 0;
    	int end = A.length - 1;
    	
    	// target��һ�γ���λ��
    	int left;
    	// target���һ�γ���λ��
    	int right;
    	
    	// ���ֲ���leftʱ����һ������targetʱ��λ��
    	int firstMeet = 0;
    	
    	// [0, A.lengt - 1]�����ֲ���left
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (A[mid] >= target) {
    			// ������һ������targetʱ��¼����
    			if (firstMeet == 0 && A[mid] == target) {
    				firstMeet = mid;
    			}
    			// �����ҷ������ƽ�left
    			end = mid;
    		} else {
    			// ������������ƽ�left
    			start = mid;
    		}
    	}
    	
    	// ���ȼ�������С������߽磬ȷ��left
    	if (A[start]  == target) {
    		left = start;
    	} else if(A[end] == target) {
    		left = end;
    	} else {
    		// ������target
    		return 0;
    	}
    	
    	// ����start������TLE
    	start = firstMeet;
    	end = A.length - 1;
    	// [firstMeet, A.length - 1]���ֲ���right
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (A[mid] <= target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	// ���ȼ�������С�����ұ߽磬ȷ��right
    	right = (A[end]  == target ? end : start);
    	
    	return right - left + 1;
    	
    }
}

//	���η��ڴ��������£�n�����µݹ�TLE
//	private int binarySearchCount(int[] A, int start, int end, int target) {
//		int count = 0;
//		
//		// �ݹ����
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
//		// ����
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
//		// �ϲ�
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
