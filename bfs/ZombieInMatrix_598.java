/**
 * BFS��α�������¼��һ��ڵ���
 * http://www.lintcode.com/en/problem/zombie-in-matrix/
 * @author yzwall
 */
package bfs;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution3 {
	// ��ʬ�ڵ�
	private class NodeInfo {
		int x, y;
		public NodeInfo(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private int ROW;
	private int COLUMN;
    public int zombie(int[][] grid) {
    	if (grid == null || grid.length == 0) {
    		return -1;
    	}
    	
    	ROW = grid.length;
    	COLUMN = grid[0].length;
    	Queue<NodeInfo> zombineQ = new ArrayDeque<>();
    	int[] deltaX = new int[]{0, 0, -1, 1};
    	int[] deltaY = new int[]{-1, 1, 0, 0};
    	int peopleCount = 0;
    	
    	// �����н�ʬ��ӣ���¼��������
    	for (int i = 0; i < ROW; i++) {
    		for (int k = 0; k < COLUMN; k++) {
    			if (grid[i][k] == 1) {
    				zombineQ.offer(new NodeInfo(i, k));
    			}
    			if (grid[i][k] == 0) {
    				peopleCount++;
    			}
    		}
    	}
    	
    	int day = 0;
    	while (!zombineQ.isEmpty() && peopleCount > 0) {
    		if (peopleCount > 0) {
    			day++;
    		}
    		
    		int size = zombineQ.size();
    		for (int i = 0; i < size; i++) {
    			NodeInfo head = zombineQ.poll();
        		for (int k = 0; k < 4; k++) {
        			// ���౻��ʬ��Ⱦ��ɽ�ʬ
            		if (isPeople(grid, head.x + deltaX[k], head.y + deltaY[k])) {
            			zombineQ.offer(new NodeInfo(head.x + deltaX[k], head.y + deltaY[k]));
            			grid[head.x + deltaX[k]][head.y + deltaY[k]] = 1;
            			peopleCount--;
            			if (peopleCount == 0) {
            				// �����Ѿ�ȫ������Ⱦ
            				return day;
            			}
            		}
        		}
    		}
    	}
    	
    	// �����Ҵ�����
    	return -1;
    }
    
    private boolean isPeople(int[][] grid, int x, int y) {
    	// ���Խ��
    	if (x < 0 || x >= ROW || y < 0 || y >= COLUMN) {
    		return false;
    	}
    	// ����Ƿ�����
    	return grid[x][y] == 0 ? true : false;
    }
}



public class ZombieInMatrix_598 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
