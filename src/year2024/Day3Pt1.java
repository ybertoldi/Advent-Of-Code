package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Pt1 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		final String REGEX = "mul\\(\\d+,\\s*\\d+\\)";		
		File file = new File("C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\2024_03.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		
		
		List<String> linhas = new ArrayList<String>();
		while ((st = br.readLine()) != null) {
			linhas.add(st);
			
		}
		
		int count = 0;
		Pattern pattern = Pattern.compile(REGEX);

		for (String linha : linhas) {
			Matcher match = pattern.matcher(linha);
			while (match.find()) {
				Pattern nums = Pattern.compile("-?\\d+");
				Matcher achaNums = nums.matcher(match.group());
				
				achaNums.find();
				int val1 = Integer.valueOf(achaNums.group());
				achaNums.find();
				int val2 = Integer.valueOf(achaNums.group());
				
				count += val1 * val2;
				
				System.out.println(match.group() + " -> " + val1 + " x " + val2);
				
			}
		}
		System.out.println(count);

	}
}
