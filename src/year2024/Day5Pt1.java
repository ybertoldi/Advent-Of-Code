package year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day5Pt1 {
	public static void main(String[] args) {
		String input = "47|53\r\n"
				+ "97|13\r\n"
				+ "97|61\r\n"
				+ "97|47\r\n"
				+ "75|29\r\n"
				+ "61|13\r\n"
				+ "75|53\r\n"
				+ "29|13\r\n"
				+ "97|29\r\n"
				+ "53|29\r\n"
				+ "61|53\r\n"
				+ "97|53\r\n"
				+ "61|29\r\n"
				+ "47|13\r\n"
				+ "75|47\r\n"
				+ "97|75\r\n"
				+ "47|61\r\n"
				+ "75|61\r\n"
				+ "47|29\r\n"
				+ "75|13\r\n"
				+ "53|13\r\n"
				+ "\r\n"
				+ "75,47,61,53,29\r\n"
				+ "97,61,53,29,13\r\n"
				+ "75,29,13\r\n"
				+ "75,97,47,61,53\r\n"
				+ "61,13,29\r\n"
				+ "97,13,75,29,47";
		
		HashMap<String, List<String>> numToList = new HashMap<String, List<String>>();
		List<String> lines = new ArrayList<String>();
		boolean changeRead = false;
		for (String line : input.split("\r\n")) {
			if (line.equals("")) {
				changeRead = true;
			}
			
			if (changeRead) {
				lines.add(line);
			}
			else {
				String vals[] = line.split("|");
				
				if (numToList.containsKey(vals[0])) {
					numToList.get(vals[0]).add(vals[1]);
				}
				else {
					List<String> temp  = new ArrayList<String>();
					temp.add(vals[1]);
					numToList.put(vals[0], temp);
				}
			}
		}
		
		
	}
}
