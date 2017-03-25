/**
 * DFS搜索求出所有回文串组合，难度较大，需多次练习加深理解
 * http://www.lintcode.com/en/problem/palindrome-partitioning/
 * @author yzwall
 */
package dfs;
import java.util.List;
import java.util.ArrayList;

class Solution11 {
    public List<List<String>> partition(String s) {
    	List<List<String>> results = new ArrayList<List<String>>();
    	if (s == null || s.length() == 0) {
    		return results;
    	}
    	
    	helper(results, new ArrayList<String>(), 0, s);
    	return results;
    }
    
    private void helper(List<List<String>> results,
    					ArrayList<String> partition,
    					int startIndex,
						String str) {
    	if (startIndex == str.length()) {
    		results.add(new ArrayList<String>(partition));
    		return;
    	}
    	
    	for (int i = startIndex; i < str.length(); i++) {
    		// 每次分割左边的字符串str[startIndex, i]
    		String subString = str.substring(startIndex, i + 1);
    		if (isPalindrome(subString)) {
    			// 左侧切割字符串回文时继续深搜
    			partition.add(subString);
    			// 检查从左侧回文字符串后边的字符串是否可分割成回文串
        		helper(results, partition, i + 1, str);
        		partition.remove(partition.size() - 1);
    		}
    	}
    }
    
    // 判断回文串
	private static boolean isPalindrome(String str) {
		int length = str.length();
		if (length == 0) {
			return false;
		}
		if (length == 1) {
			return true;
		}
		boolean isPalindrome = true;
		for (int i = 0, j = length - 1; i < length / 2; i++, j--) {
			if (str.charAt(i) != str.charAt(j)) {
				isPalindrome = false;
			}
		}
		return isPalindrome;
	}
}

public class PalindromePartitioning_136 {
	
	public static void main(String[] args) {
		String str = "abba";
		System.out.println(new Solution11().partition(str));
	}
}
