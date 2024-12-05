package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Day1Pt2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		File file = new File("C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\pairs.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		List<Integer> l = new ArrayList<Integer>();
		List<Integer> r = new ArrayList<Integer>();

		while ((st = br.readLine()) != null) {
			String[] splitado = st.split("   ");
			l.add(Integer.valueOf(splitado[0]));
			r.add(Integer.valueOf(splitado[splitado.length - 1]));
		}
		
		Collections.sort(l);
		Collections.sort(r);
		
		int similarity = 0;
		
		for(int num : l) {
			similarity += num * Collections.frequency(r, num);
		}
	
		System.out.println(similarity);
	}
}
