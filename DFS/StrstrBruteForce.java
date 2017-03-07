package DFS;
/**
 * http://www.lintcode.com/zh-cn/problem/strstr/
 * @author yzwall
 */

/**
 * 暴力O(n^2)匹配 
 */
class Solution3 {
	
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
    	// 先做异常检测
    	if(source == null || target == null) {
    		return -1;
    	}
    	
    	int sourceLen = source.length();
    	int targetLen = target.length();
    	for(int i = 0; i <= sourceLen - targetLen; i++) {
    		int k;
    		for(k = 0; k < targetLen; k++) {
    			if(source.charAt(i + k) != target.charAt(k)) {
    				break;
    			}
    		}
    		if(k == targetLen) {
    			return i;
    		}
    	}
    	return -1;
    }
}



public class StrstrBruteForce {

	public static void main(String[] args) {
		
		String source = "null";
		String target = "a";
		System.out.println(new Solution3().strStr(source, target));
	}

}
