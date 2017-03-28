/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-preorder-traversal/
 * ������ǰ���������������ݹ�ʵ�� & ���η��ݹ�ʵ�� & �ǵݹ�ʵ��
 * @author yzwall
 */
package binarytree;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * ������ǰ�����-����ݹ�ʵ��
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
        // �����������ڵ�
        path.add(root.val);	
        // ����������
        traverse(root.left, path); 
        // ����������
        traverse(root.right, path);
    }
}

/**
 * ������ǰ�����-���η�ʵ��
 */
class Solution1 {
	
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	ArrayList<Integer> results = new ArrayList<Integer>();
    	
    	// �ݹ����
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
 * ������ǰ�����-�ǵݹ�ʵ�֣�
 * ������ջʵ��
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
        	// ջ����ջ
        	Pair rootNode = stack.pop();
        	if (rootNode.root == null) {
        		continue;
        	}
        	// ����ջ���ظ�Ԫ��
        	if (rootNode.isVisited) {
        		results.add(rootNode.root.val);
        	} else {
        		// ǰ����ջ˳���ң��󣬸� �������ҷ�������ջ��
        		stack.push(new Pair(rootNode.root.left, false));	// ����˳��ǰ��ĺ���ջ�������ȳ��ȱ���
        		stack.push(new Pair(rootNode.root.right, false));	// ����˳����������ջ�������������
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
