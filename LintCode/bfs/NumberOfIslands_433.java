/**
 * BFS求图（矩阵形式）中连通分量个数
 * http://www.lintcode.com/en/problem/number-of-islands/
 * @author yzwall
 */

package bfs;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution2 {
	private class NodeInfo {
		int x;
		int y;
		//boolean isVisited;
		public NodeInfo(int x, int y) {
			this.x = x;
			this.y = y;
			//this.isVisited = isVisited;
		}
	}
	
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
	private int COLUMN;
	private int ROW;
    public int numIslands(boolean[][] grid) {
    	if (grid == null || grid.length == 0) {
    		return 0;
    	}
    	ROW = grid.length;
    	COLUMN = grid[0].length;
    	int[] deltaX = new int[]{0, 0, -1, 1};
    	int[] deltaY = new int[]{-1, 1, 0, 0};
    	int directNum = 4;
    	int totalCount = 0;
    	
    	boolean[][] isVisited = new boolean[ROW][COLUMN];
    	Queue<NodeInfo> queue = new ArrayDeque<NodeInfo>();
    	for (int i = 0; i < ROW; i++) {
    		for (int k = 0; k < COLUMN; k++) {
    			// 找到新的连通分量
    			if (!isVisited[i][k] && grid[i][k]) {
    				queue.offer(new NodeInfo(i, k));
    				int count = 0;
    				while (!queue.isEmpty()) {
    					count = 1;
    					NodeInfo head = queue.poll();
    					isVisited[head.x][head.y] = true;
    					// 遍历上下左右
    					for (int j = 0; j < directNum; j++) {
        					if (isValid(grid, isVisited, head.x + deltaX[j], head.y + deltaY[j])) {
        						queue.offer(new NodeInfo(head.x + deltaX[j], head.y + deltaY[j]));
        					}
    					}
    				}
    				totalCount += count;
    			}
    		}
    	}
    	return totalCount;
    }
    
    private boolean isValid(boolean[][] grid, boolean[][] isVisited,
    						int x, int y) {
    	if (x < 0 || x >= ROW || y < 0 || y >= COLUMN) {
    		return false;
    	}
    	if (!isVisited[x][y] && grid[x][y]) {
    		return true;
    	}
    	return false;
    }
}


public class NumberOfIslands_433 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] grid = new boolean[][] {{true,true,false,false,false},
											{false,true,false,false,true},
											{false,false,false,true,true},
											{false,false,false,false,false},
											{false,false,false,false,true}};
		System.out.println(new Solution2().numIslands(grid));

	}

}
