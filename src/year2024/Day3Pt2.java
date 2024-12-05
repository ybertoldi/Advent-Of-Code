package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Pt2 {
	public static void main(String[] args) throws IOException {
		final String REGEX = "mul\\(\\d+,\\s*\\d+\\)";
		File file = new File(
				"C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\2024_03.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		int count = 0;
		int count2 = 0;
		String texto = "";

		List<String> linhas = new ArrayList<String>();
		while ((st = br.readLine()) != null) {
			linhas.add(st);
			texto += st;
		}
		
		String[] donts = texto.split("don't\\(\\)");
		Pattern pattern = Pattern.compile(REGEX);
		
		for (int i = 0; i < donts.length; i++) {
			
			String valor;
			if (i == 0) {
				valor = donts[0];
			} else {
				String[] vals = donts[i].split("do\\(\\)", 2);
				valor = (vals.length == 2)? vals[1]: "";
			}
			
			
			Matcher match = pattern.matcher(valor);
			
			System.out.println("Trecho 1 :" + valor);
			
			while (match.find()) {
				Pattern nums = Pattern.compile("-?\\d+");
				Matcher achaNums = nums.matcher(match.group());

				achaNums.find();
				int val1 = Integer.valueOf(achaNums.group());
				achaNums.find();
				int val2 = Integer.valueOf(achaNums.group());

				count2 += val1 * val2;
			}
		}
		
		System.out.println(count2);
	}
}
