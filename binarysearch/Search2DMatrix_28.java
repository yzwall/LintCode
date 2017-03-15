/**
 * http://www.lintcode.com/zh-cn/problem/search-a-2d-matrix/
 * �������ά�����в���Ԫ�أ���������������ӽ�Ԫ�صı���
 */
package binarysearch;

/**
 * ���ж����ҳ���0����target��maxmin�����꣬
 * Ȼ����ָ�����ж�target���ж��ֲ���
 * @author yzwall
 */
class Solution6 {

	private int column;
	private int line;
	
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
    	// �쳣���߽���
    	if(matrix == null || 0 == matrix.length) {
    		return false;
    	}
    	line = matrix.length;
    	column = matrix[0].length;
    	// Ѱ��minmax�����в�ʶ������������ǰ���б߽���
    	if(matrix[0][0] > target || matrix[line - 1][column - 1] < target) {
    		return false;
    	}
    	
    	// �ڵ�0���У��ҳ�target��maxmin���±�
    	int index = findColumnMaxMinIndex(matrix, target);
    	
    	// target = matrix[0][0]
    	if(index == 0) {
    		return true;
    	}
    	
    	// target�ھ������һ��
    	if(index == line) {
    		return binaryLineSearch(matrix, line - 1, target);
    	}
    	
    	// target�ھ���index - 1��
    	return binaryLineSearch(matrix, index - 1, target);
    }
    
    
    /**
     * ���ַ� O(log(n))
     * @param lineNum ������ָ���У��ڸ��н��ж��ֲ���
     * @return �Ծ����н��ж��ֲ��ң����ҳɹ�����true�����򷵻�false
     */
	private boolean binaryLineSearch(int[][] matrix, int lineNum, int target) {
		int start = 0;
		int end = column - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if(matrix[lineNum][mid] == target) {
				return true;
			}
			if(matrix[lineNum][mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		
		if(matrix[lineNum][start] == target || matrix[lineNum][end] == target) {
			return true;
		}
		
		return false;
	}

	/**
	 * ���ַ� O(log(m))
	 * @return �ҳ�target�ڵ�0���е����ȳ��ֵ�maxmin�����±�
	 */
	private int findColumnMaxMinIndex(int[][] matrix, int target) {
		int start = 0;
		int end = line - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if(matrix[mid][0] == target) {
				start = mid;
			} else if(matrix[mid][0] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		
		if(matrix[start][0] >= target) {
			return start;
		} 
		if(matrix[end][0] >= target) {
			return end;
		} 
		
		return line;
	}
}


public class Search2DMatrix_28 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int dimNum = 4;
		int[][] matrix = new int [][]{	
										{1, 2, 3, 5},
										{7, 9, 13, 16},
										{19, 21, 25, 30},
										{31, 33, 34, 36}
									 };
		
		System.out.println(new Solution6().searchMatrix(matrix, 33));
		System.out.println(new Solution6().searchMatrix(matrix, 34));
		System.out.println(new Solution6().searchMatrix(matrix, 1));
		
		int[][] matrix1 = new int [][]{};		
		 
		System.out.println(new Solution6().searchMatrix(matrix1, 1));
	
		

	}

}
