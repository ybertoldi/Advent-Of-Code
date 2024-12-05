package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day4Pt1 {
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
		
		char[][] matrix = new char[lines.length][lines[0].length()];

		for (int i = 0; i < lines.length; i++) {
			matrix[i] = lines[i].toCharArray();
		}

		int yLen = matrix.length;
		int xLen = matrix[0].length;
		int counter = 0;
		
		for (int lin = 0; lin < yLen; lin++) {
			for(int col = 0; col < xLen; col++) {
				if (matrix[lin][col] == 'X') {
					counter += calculaInstancias(lin,col,matrix);
				}
			}
		}
		
		System.out.println(counter);
	}

	private static int calculaInstancias(int lin, int col, char[][] matrix) {
		int counter = 0;
		
		for (int dLin = -1; dLin <= 1; dLin++) {
			for (int dCol = -1; dCol <= 1; dCol++) {
				
				if (lin + 3*dLin >= matrix.length || lin + 3*dLin < 0 || col + 3*dCol >= matrix[0].length || col + 3*dCol < 0) {}
				else {
					boolean p1 = matrix[lin + dLin][col + dCol] == 'M';
					boolean p2 = matrix[lin + 2*dLin][col + 2*dCol] == 'A';
					boolean p3 = matrix[lin + 3*dLin][col + 3*dCol] == 'S';
					if (p1 && p2 && p3) {
						counter++;
					}
				}
				
			}
		}
		return counter;
	}
}
