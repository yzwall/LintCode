/**
 * http://www.lintcode.com/en/problem/smallest-rectangle-enclosing-black-pixels/
 * ��С���Ǿ��Σ�ͨ��ָ�����ص�(x,y)�ֱ����Ĵζ��ֱƽ�
 * @author yzwall
 */
package BinarySearch;

class Solution15 {
	private boolean[] row;	
	private boolean[] column;
	private int ROW;	
	private int COLUMN;
	private final int COLUMN_FLAG = 1;
	private final int ROW_FLAG = 0;

    /**
     * @param image a binary matrix with '0' and '1'
     * @param x, y the location of one of the black pixels
     * @return an integer
     */
    public int minArea(char[][] image, int x, int y) {
    	if (image == null || image.length == 0 || image[0].length == 0) {
    		return 0;
    	}
    	
    	setPara(image);
    	
    	// ���ֲ�����߽�:��һ���������ص���б�
    	int leftBound = findLeft(image, 0, y);
    	
    	// ���ֲ����ұ߽�:���һ���������ص���б�
    	int rightBound = findRight(image, y, COLUMN - 1);
    	
    	// ���ֲ����ϱ߽磺��һ���������ص���б�
    	int upBound = findUp(image, 0, x);
    	
		// ���ֲ����±߽磺���һ���������ص���б�
    	int bottomBound = findBottom(image, x, ROW - 1);
    	
    	return (rightBound - leftBound + 1) * (bottomBound - upBound + 1);
    }

	/**
     * ��������߽磬��һ���������ص����
     * �൱����OOOOOXXXXX���ҵ�1��X���ֵ�λ��
     * @param start ��0��
     * @param end ��x��
     * @return ��һ���������ص���б�
     */
    private int findLeft(char[][] image, int start, int end) {
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (isContainsPixels(image, mid, COLUMN_FLAG)) {
				// �����Ҳ࣬end�ƽ�firstIndex
				end = mid;
			} else {
				// �������O����
				start = mid;
	    	}
		}
		
		// ���ȼ�����������࣬ȷ��firstIndex
		if(isContainsPixels(image, start, COLUMN_FLAG)) {
			return start;
		}
		return end;
    }
    
    /**
     * �������ұ߽�
     * �൱��XXXXOOOOO�������һ��X���ֵ�λ��
     * @param start ��x��
     * @param end ���һ��
     * @return ���һ���������ص���б�
     */
    private int findRight(char[][] image, int start, int end) {
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isContainsPixels(image, mid, COLUMN_FLAG)) {
    			// �������X���򣬱ƽ�lastIndex
    			start = mid;
    		} else {
    			// �����Ҳ�O����
    			end = mid;
    		}
    	}
    	
    	// ���ȼ����������Ҳ࣬ȷ��lastIndex
    	if (isContainsPixels(image, end, COLUMN_FLAG)) {
    		return end;
    	}
		return start;
	}
    
    /**
     * �����ҳ��ϱ߽�
     * �൱����OOOOOXXXX���ҳ���һ��X����λ��
     * @param start ��0��
     * @param end ��x��
     * @return ��һ���������ص���б�
     */
	private int findUp(char[][] image, int start, int end) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (isContainsPixels(image, mid, ROW_FLAG)) {
				// �����²�X���򣬱ƽ�firstIndex
				end = mid;
			} else {
				// �����ϲ�O����
				start = mid;
			}
		}
		
		// ���ȼ�����������(��)�࣬ȷ��firstIndex
		if (isContainsPixels(image, start, ROW_FLAG)) {
			return start;
		}
		return end;
	}
	
    /**
     * �����ҳ��±߽�
     * �൱����XXXXOOOO���ҵ���һ��X����λ��
     * @param start ��x��
     * @param end ���һ��
     * @return ���һ���������ص���б�
     */
	private int findBottom(char[][] image, int start, int end) {
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (isContainsPixels(image, mid, ROW_FLAG)) {
				// �����ϲ�X���򣬱ƽ�lastIndex
				start = mid;
			} else {
				// �����²�O����
				end = mid;
			}
		}
		
		// ���ȼ�����������(��)�࣬ȷ��lastIndex;
		if(isContainsPixels(image, end, ROW_FLAG)) {
			return end;
		}
		
		return start;
	}
	
    // ������ʼ��
    private void setPara(char[][] image) {
    	ROW = image.length;
    	COLUMN = image[0].length;
    	row = new boolean[ROW];
    	column = new boolean[COLUMN];
    }
    
    /**
     * ����n��/���Ƿ�������ص�
     * @param flag	0	����n��
     * 			  	1	 ����n��
     * @return true ����/�д������ص�
     */
    private boolean isContainsPixels(char[][]image, int n, int flag) {
    	// ����n��
    	if (flag == ROW_FLAG) {
    		if (row[n]) {
    			return true;
    		}
    		for (int i = 0; i < COLUMN; i++) {
    			if(image[n][i] == '1') {
    				row[n] = true;
    				column[i] = true;
    				return true;
    			}
    		}
    		return false;
    	} else {	// ����n��
    		if (column[n]) {
    			return true;
    		}
    		for (int i = 0; i < ROW; i++) {
    			if(image[i][n] == '1') {
    				row[i] = true;
    				column[n] = true;
    				return true;
    			}
    		}
    		return false;    		
    	}
    }
}


public class SmallEclosingRectangle {

	public static void main(String[] args) {
		char[][] image = {
							{'0', '0', '1', '0'},
							{'0', '1', '1', '0'},
							{'0','1', '0', '0'}
						 };
		System.out.println(new Solution15().minArea(image, 0, 2));
	}

}
