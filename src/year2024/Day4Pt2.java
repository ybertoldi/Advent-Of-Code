package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day4Pt2 {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\XMAS.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;

		String[] lines = new String[140];

		int j = 0;
		while ((st = br.readLine()) != null) {
			lines[j] = st;
			j++;
		}
		System.out.println(lines[0].length());


		char[][] matrix = new char[lines.length][lines[0].length()];

		for (int i = 0; i < lines.length; i++) {
			matrix[i] = lines[i].toCharArray();
		}

		int yLen = matrix.length;
		int xLen = matrix[0].length;
		int counter = 0;

		for (int lin = 0; lin < yLen; lin++) {
			for(int col = 0; col < xLen; col++) {
				if (matrix[lin][col] == 'A') {
					if (lin - 1 >= 0 && lin + 1 < yLen && col - 1 >= 0 && col + 1 < xLen) {
						String word = "";
						word += matrix[lin - 1][col - 1];
						word += matrix[lin - 1][col + 1];
						word += matrix[lin + 1][col + 1];
						word += matrix[lin + 1][col - 1];

						if (word.equals("MSSM") || word.equals("SSMM") || word.equals("SMMS") || word.equals("MMSS")) {
							counter++;
						}
					}
				}
			}
		}

		System.out.println(counter);
	}
}
