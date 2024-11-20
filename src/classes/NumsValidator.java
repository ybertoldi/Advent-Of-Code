package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumsValidator {

	HashMap<List<Integer>, Integer> oldNums = new HashMap<List<Integer>, Integer>();
	HashMap<List<Integer>, Integer> newNums = new HashMap<List<Integer>, Integer>();
	
	List<Integer> oldSymbolPos = new ArrayList<Integer>();
	List<Integer> newSymbolPos = new ArrayList<Integer>();

	
	int curNum = 0;
	int curNumStart = -1;
	int curNumEnd = -1;
	int counter = 0;
	boolean empty = true;
	
	
	public void add(char numChar, int pos) {
		curNum = curNum * 10 + (numChar - 48);
		if (empty) {
			curNumStart = pos;
			empty = false;
		}
		curNumEnd = pos;
	}
	
	public void foundSymbolAt(int pos) {
		newSymbolPos.add(pos);
		if (!empty) {
			endNum();
		}
	}
	
	public void endNum() {
		boolean found = false;
		for(int pos : oldSymbolPos) {
			if (!found && pos >= curNumStart - 1 && pos <= curNumEnd + 1) {
				counter += curNum;
				found = true;
				System.out.printf(curNum + " Adicionado | ");
			}
		}
		
		if (!found) {
			newNums.put(Arrays.asList(curNumStart, curNumEnd) , curNum);
		}
		
		empty = true;
		curNum = 0;
		curNumStart = -1;
		curNumEnd = -1;
	}
	
	public void newLine() {
		for(Map.Entry<List<Integer>, Integer> entry: oldNums.entrySet()) {
			List<Integer> limits = entry.getKey();
			int val = entry.getValue();
			
			boolean cond = true;
			int i = 0;
			while (i < newSymbolPos.size() && cond) {
				int pos = newSymbolPos.get(i);
				if (pos >= limits.get(0) - 1 && pos <= limits.get(1) + 1) {
					counter += val;
					cond = false;
					System.out.printf(val + " Adicionado | ");
				}
				i++;
			}
			
		}
		
		for(Map.Entry<List<Integer>, Integer> entry: newNums.entrySet()) {
			List<Integer> limits = entry.getKey();
			int val = entry.getValue();
			
			boolean cond = true;
			int i = 0;
			while (i < newSymbolPos.size() && cond) {
				int pos = newSymbolPos.get(i);
				if (pos == limits.get(0) - 1 || pos == limits.get(1) + 1) {
					counter += val;
					cond = false;
					System.out.printf(val + " Adicionado | ");
				}
				i++;
			}
			
		}

		
		oldNums.clear();
		oldNums.putAll(newNums);
		newNums.clear();
		
		oldSymbolPos.clear();
		oldSymbolPos.addAll(newSymbolPos);
		newSymbolPos.clear();
		
		System.out.println();
	}
	
	public boolean isWithNumOpen() {
		return !empty;
	}
	
	public int getSum() {
		return counter;
	}
}
