package doublepointers;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 设计数据结构实现add和find操作，代码TLE待优化
 * http://www.lintcode.com/zh-cn/problem/two-sum-data-structure-design/
 * @author yzwall
 */
class Solution17 {
	private HashMap<Integer, Boolean> map = new HashMap<>();
	private HashSet<Integer> addSet = new HashSet<>();
	
	 // Add the number to an internal data structure.
    public void add(int number) {
    	map.put(number, true);
    	addSet.add(number);
    	for (Integer key : addSet) {
    		map.put(key + number, true);
    	}
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
    	return map.containsKey(value);
    }
}


public class TwoSum_DataStructureDesign_607 {
	public static void main(String[] args) {

		Solution17 ss = new Solution17();
		ss.add(1);
		ss.add(3);
	}
}
