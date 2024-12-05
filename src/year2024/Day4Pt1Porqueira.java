package year2024;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4Pt1Porqueira {
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
		int count = 0;

		for (int i = 0; i < lines.length; i++) {
			matrix[i] = lines[i].toCharArray();
		}

		int yLen = matrix.length;
		int xLen = matrix[0].length;

		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				char curChar = matrix[y][x];
				if (curChar == 'X') {
					count += achaInstancias(x, y, matrix);

				}

			}
		}

		System.out.println(count);
	}

	private static int achaInstancias(int x, int y, char[][] matrix) {
		final char[] TOKEN = new char[] { 'X', 'M', 'A', 'S' };
		int count = 0;
		int lenX = matrix[0].length;
		int lenY = matrix.length;

		int lx = x - 1;
		boolean l = x - 3 >= 0;

		int rx = x + 1;
		boolean r = x + 3 < lenX;

		int uy = y - 1;
		boolean u = y - 3 >= 0;

		int dy = y + 1;
		boolean d = y + 3 < lenY;

		boolean ul = u && l;
		boolean ur = u && r;
		boolean dl = d && l;
		boolean dr = d && r;

		boolean cond = l || r || u || d || ul || ur || dl || dr;
		int i = 1;

		while (cond) {

			if (l) {
				if (matrix[y][lx] != TOKEN[i]) {
					l = false;
				} else {
					if (i == 3) {
						count++;
					}
				}
			}

			if (r) {
				if (matrix[y][rx] != TOKEN[i]) {
					r = false;
				} else {
					if (i == 3) {
						count++;
					}
				}
			}

			if (u) {
				if (matrix[uy][x] != TOKEN[i]) {
					u = false;
				} else {
					if (i == 3) {
						count++;
					}
				}
			}

			if (d) {
				if (matrix[dy][x] != TOKEN[i]) {
					d = false;
				} else {
					if (i == 3) {
						count++;
					}
				}
			}

			if (ul) {
				if (matrix[uy][lx] != TOKEN[i]) {
					ul = false;
				} else if (i == 3) {
					count++;
				}
			}

			if (ur) {
				if (matrix[uy][rx] != TOKEN[i]) {
					ur = false;
				} else if (i == 3) {
					count++;
				}
			}

			if (dl) {
				if (matrix[dy][lx] != TOKEN[i]) {
					dl = false;
				} else if (i == 3) {
					count++;
				}
			}
			
			if (dr) {
				if (matrix[dy][rx] != TOKEN[i]) {
					dr = false;
				} else if (i == 3) {
					count++;
				}
			}
			
			
			i++;
			
			lx--;
			rx++;
			dy++;
			uy--;
			
			cond = i < 4;
		}

		return count;
	}
}
