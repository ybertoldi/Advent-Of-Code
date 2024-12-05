package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day5Pt1 {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\printQueue.txt");
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
			List<String> vals = Arrays.asList(line.split(","));
			boolean valid = true;
			for (int i = 0; i < vals.size(); i++) {
				String val = vals.get(i);
				if (!numToList.containsKey(val)) {
					continue;
				}

				for (String s : numToList.get(val)) {
					if (vals.contains(s) && vals.indexOf(s) < i) {
						valid = false;
						break;
					}
				}
			}

			if (valid) {
				counter += Integer.valueOf(vals.get(vals.size() / 2));
			}
		}

		System.out.println(counter);
	}
}
