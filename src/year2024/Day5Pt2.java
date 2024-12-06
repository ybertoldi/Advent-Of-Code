package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import classes.Day5Comparator;

public class Day5Pt2 {
	public static void main(String[] args) throws IOException {
		File file = new File(
				"C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\printQueue.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String l;

		HashMap<String, List<String>> numToList = new HashMap<String, List<String>>();
		List<String> lines = new ArrayList<String>();
		boolean changeRead = false;

		while ((l = br.readLine()) != null) {
			if (l.equals("")) {
				changeRead = true;
				continue;
			}

			if (changeRead) {
				lines.add(l);
			} else {
				String vals[] = l.split("[|]");

				if (numToList.containsKey(vals[0])) {
					numToList.get(vals[0]).add(vals[1]);
				} else {
					List<String> temp = new ArrayList<String>();
					temp.add(vals[1]);
					numToList.put(vals[0], temp);
				}
			}
		}

		int counter = 0;
		for (String line : lines) {
			List<String> vals =  new LinkedList<String>(Arrays.asList(line.split(",")));
			
			boolean valid = true;
			for (int i = 0; i < vals.size(); i++) {
				String val = vals.get(i);
				boolean canBreak = false;
				if (!numToList.containsKey(val)) {
					continue;
				}

				for (String s : numToList.get(val)) {
					if (vals.contains(s) && vals.indexOf(s) < i) {
						valid = false;
						canBreak = true;
						break;
					}
				}
				
				if (canBreak) {
					break;
				}
			}

			if (!valid) {
				System.out.println(vals);
				sortList(vals, numToList);
				counter += Integer.valueOf(vals.get(vals.size() / 2));
			}
		}

		System.out.println(counter);
	}
	
	static void sortList(List<String> vals, HashMap<String, List<String>> data) {
			int i = vals.size() -1;
			List<String> verifiedNums = new ArrayList<String>();
			
			while (i >= 0) {
				String cur = vals.get(i);
				int curI = i;
				
				if (verifiedNums.contains(cur)) {
					i--;
					continue;
				}
				
				if (!data.containsKey(cur)) {
					i--;
					continue;}
				
				List<String> compareList = data.get(cur);
				for (int j = i - 1; j >= 0; j--) {
					
					if (compareList.contains(vals.get(j)) && j < curI) {
						String changedVal = vals.remove(j);
						vals.add(curI , changedVal);
						curI--;
						
						System.out.println(vals + " " + cur + " comes before " + changedVal);
					}
				}
				verifiedNums.add(cur);
			}
		

		
		
	}
}
