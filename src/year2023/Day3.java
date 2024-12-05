package year2023;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\engine.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String input = "";
		String st;

		while ((st = br.readLine()) != null) {
			input += st + "\r\n";
		
		}

		br.close();

		// gera matriz de caracteres
		String[] lines = input.split("\r\n");
		char[][] matriz = new char[lines[0].length()][lines.length];
		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[0].length; j++) {
				matriz[j][i] = lines[i].charAt(j);
				System.out.print(matriz[j][i]);
			}
			System.out.println();
		}

		int yLen = matriz.length;
		int xLen = matriz[0].length;
		List<Integer> numsValidados = new ArrayList<Integer>();

		// navega a matriz
		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				char curChar = matriz[x][y];

				
				if (Character.isDigit(curChar)) {
					int num = curChar - '0'; // '3' -> 3
					int startX = x;
					int endX = x;

					if (x + 1 < xLen) {
						boolean podePegarProximo = true;
						int curX = x + 1;
						char nextChar = matriz[curX][y];

						while (podePegarProximo && Character.isDigit(nextChar)) {
							endX = curX;
							num = num * 10 + (nextChar - '0'); // se era 3 e o proximo eh 4 -> num = 34

							curX++;
							if (curX < xLen) {
								nextChar = matriz[curX][y];
							} else {
								podePegarProximo = false;
							}
						}

						int limEsquerdoX = (startX - 1 <= 0) ? startX : startX - 1;
						int limDireitoX = (endX + 1 >= xLen) ? endX : endX + 1;

						int limSuperiorY = (y - 1 <= 0) ? y : y - 1;
						int limInferiorY = (y + 1 >= yLen) ? y : y + 1;
						boolean valido = false;

						for (int k = limEsquerdoX; k <= limDireitoX; k++) {
							char charSup = matriz[k][limSuperiorY];
							char charInf = matriz[k][limInferiorY];

							if (!Character.isDigit(charSup) && charSup != '.') {
								valido = true;
							}
							if (!Character.isDigit(charInf) && charInf != '.') {
								valido = true;
							}
						}

						if (!Character.isDigit(matriz[limEsquerdoX][y]) && matriz[limEsquerdoX][y] != '.') {
							valido = true;
						}

						if (!Character.isDigit(matriz[limDireitoX][y]) && matriz[limDireitoX][y] != '.') {
							valido = true;
						}

						if (valido) {
							numsValidados.add(num);
						}
						x = endX;
					}

				}

			}
		}

		int sum = 0;
		for (int val : numsValidados) {
			sum += val;
		}

		System.out.println(numsValidados);
		System.out.println(sum);

	}

}
