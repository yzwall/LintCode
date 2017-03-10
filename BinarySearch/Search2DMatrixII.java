/**
 * http://www.lintcode.com/en/problem/search-a-2d-matrix-ii/
 * @author yzwall
 */
package BinarySearch;

/**
 * @author yzwall
 */
class Solution8 {
	private int count;
	private int row;
	private int column;
	/**
	 * ����target�ھ����г��ֵĴ����ܺ�
	 * @param matrix
	 * @param target
	 * @return ����target�ھ����г��ֵĴ����ܺ�
	 */
	public int searchMatrix(int[][] matrix, int target) {
		
		if (matrix == null || 0 == matrix.length) {
			return 0;
		}
		for (int i=0; i<matrix.length; i++) {
			if(matrix[i].length == 0) {
				return 0;
			}
		}
		
		count = 0;
		row = matrix.length - 1;
		column = matrix[0].length - 1;
		findTarget(matrix, row, 0, target);
		
		return count;
		
	}
	
	/**
	 * ÿ��ȡ�Ӿ�������Ԫ�غ�target�Ƚϣ��ݹ����
	 * @param i ��ǰ����Ԫ�ص����±�
	 * @param j ��ǰ����Ԫ�ص����±�
	 */
	private void findTarget(int[][] matrix, int i, int j, int target) {
		// �ݹ��׼����
		if (i < 0 || i > row || j < 0 || j > column) {
			return;
		}
		
		// �ݹ�ֽ�
		if (matrix[i][j] == target) {
			count++;
			// ������i�У���j��
			findTarget(matrix, i - 1, j + 1, target);
		} else if (matrix[i][j] < target) {
			// ������j��
			findTarget(matrix, i, j + 1, target);
		} else {
			// ������i��
			findTarget(matrix, i - 1, j, target);
		}
	}
}

public class Search2DMatrixII {

	public static void main(String[] args) {
		int[][] matrix = {{1, 3, 5, 7},
						  {2, 4, 7, 8},
						  {3, 8, 9, 10}};
		//int[][] matrix = {{},{}};
		System.out.println(new Solution8().searchMatrix(matrix, 11));
	}

}
