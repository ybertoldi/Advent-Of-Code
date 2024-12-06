package classes;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Day5Comparator implements Comparator<String>{
	HashMap<String, List<String>> data;
	
	public Day5Comparator(HashMap<String, List<String>> data) {
		this.data = data;
	}
	
	@Override
	public int compare(String o1, String o2) {
		if (data.containsKey(o1) && data.get(o1).contains(o2)) {
			return 1;
		}
		
		if (data.containsKey(o2) && data.get(o2).contains(o1)) {
			return -1;
		}
		
		return 0;
	}

}
