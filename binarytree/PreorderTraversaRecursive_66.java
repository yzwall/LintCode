/**
 * http://www.lintcode.com/zh-cn/problem/binary-tree-preorder-traversal/
 * ������ǰ���������������ݹ�ʵ�� & ���η��ݹ�ʵ��
 * @author yzwall
 */
package binarytree;
import java.util.ArrayList;

// ����ݹ�ʵ��
class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
    	
        if (root == null) {
            return new ArrayList<Integer>();
        }
        
        ArrayList<Integer> results = new ArrayList<Integer>();
        traverse(root, results);
        
        return results;
    }
    
    /**
     * ǰ���������ݹ�ʵ�֣��������ҡ�
     */
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

// ���η��ݹ�ʵ��
class Solution1 {
    /**
     * @param root: The root of binary tree.
     * @return: Preorder in ArrayList which contains node values.
     */
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


public class PreorderTraversaRecursive_66 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
