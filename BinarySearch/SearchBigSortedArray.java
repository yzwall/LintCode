/**
 * http://www.lintcode.com/zh-cn/problem/search-in-a-big-sorted-array/
 * ��Ŀ����Ϊ�ڲ�֪���������鳤�ȵ�����£��ҵ�ָ��Ԫ���������λ��
 * ��ȡ�Ŵ�endȷ�����ֱ߽磬Ȼ���������ڶ��ֲ����������λ�ã���Ŀ���ݲ��Ͻ�û�м��Խ�����
 * 
 * @author yzwall
 */
package BinarySearch;

class Solution10 {
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
    	// �߽���
    	if(!isExists(reader, 0)) {
    		return -1;
    	}
    	
    	// ���������Ŵ�end��ȷ�������ұ߽�
    	int right = 0;
    	int left = 2;
    	while(isExists(reader, left) && reader.get(left) < target) {
    		right = left;
    		// λ����Ч�ʸ�
    		left = left << 1;
    	}  
    	
    	int firstIndex = Integer.MAX_VALUE;
    	int start = right;
    	int end = left;
    	while(start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		int midValue = reader.get(mid);
    		if(midValue == target) {
    			firstIndex = Math.min(firstIndex, mid);
    			end = mid;
    		} else if(midValue < target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	
    	// ���ȼ����߽磬ȷ���������
    	if(reader.get(start) == target) {
    		return Math.min(firstIndex, start);
    	}
    	if(reader.get(end) == target) {
    		return Math.min(firstIndex, end);
    	}   	
    	
    	return firstIndex < Integer.MAX_VALUE ? firstIndex : -1;
    }
    
    private boolean isExists(ArrayReader reader, int index) {
    	if(reader.get(index) == -1 || reader.get(index) == Integer.MAX_VALUE) {
    		return false;
    	}    	
    	return true;
    }
    
}

/**
 * ��Ӳ����࣬ģ����Ŀ�ӿ�
 * @author yzwall
 */
class ArrayReader {
	// ��Ӳ��Ա�����ģ�����汾������
	private int[] nums = new int[]{1, 1, 1, 1, 11, 11, 88};
	
	/**
	 * ��Ӳ��Է���
	 * �жϴ���汾�������е�k�汾�Ƿ����
	 */
	public int get(int index) {
		if (index < 0) {
			return -1;
		} 
		if( index >= nums.length) {
			return Integer.MAX_VALUE;
		}
		return nums[index];
	}
}


public class SearchBigSortedArray {

	public static void main(String[] args) {
		System.out.println(new Solution10().searchBigSortedArray(new ArrayReader(), 1));
	}

}
