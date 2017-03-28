/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-preorder-traversal/
 * 二叉树前序遍历，经典遍历递归实现 & 分治法递归实现 & 非递归实现
 * @author yzwall
 */
package binarytree;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 二叉树前序遍历-经典递归实现
 */
class Solution {
	
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> results = new ArrayList<Integer>();
        traverse(root, results);
        return results;
    }
    
    private void traverse(TreeNode root, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }
        // 遍历子树根节点
        path.add(root.val);	
        // 遍历左子树
        traverse(root.left, path); 
        // 遍历右子树
        traverse(root.right, path);
    }
}

/**
 * 二叉树前序遍历-分治法实现
 */
class Solution1 {
	
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> results = new ArrayList<Integer>();
    	
    	// 递归出口
        if (root == null) {
            return results;
        }
        
        // divide & conquer
        ArrayList<Integer> left = preorderTraversal(root.left);
        ArrayList<Integer> right = preorderTraversal(root.right);
        
        // merge
        results.add(root.val);
        results.addAll(left);
        results.addAll(right);
        
        return results;
    }
}

/**
 * 二叉树前序遍历-非递归实现，
 * 逆序入栈实现
 */
class Solution2 {
	private class Pair {
		TreeNode root;
		boolean isVisited;
		public Pair(TreeNode root, boolean isVisited) {
			this.root = root;
			this.isVisited = isVisited;
		}
	}
	
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> results = new ArrayList<Integer>();
        if (root == null) {
            return results;
        }
        
        ArrayDeque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(root, false));
        while (!stack.isEmpty()) {
        	// 栈顶出栈
        	Pair rootNode = stack.pop();
        	if (rootNode.root == null) {
        		continue;
        	}
        	// 访问栈顶重复元素
        	if (rootNode.isVisited) {
        		results.add(rootNode.root.val);
        	} else {
        		// 前序入栈顺序：右，左，根 （根左右翻过来入栈）
        		stack.push(new Pair(rootNode.root.left, false));	// 排在顺序前面的后入栈，后入先出先遍历
        		stack.push(new Pair(rootNode.root.right, false));	// 排在顺序后面的先入栈，先入后出后遍历
        		stack.push(new Pair(rootNode.root, true));
        	}
        }
        return results;
    }
}

public class PreorderTraversal_66 {
	public static void main(String[] args) {
	}
}
