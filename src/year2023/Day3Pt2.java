package year2023;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3Pt2 {
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
			}
		}

		int yLen = matriz.length;
		int xLen = matriz[0].length;
		List<int[]> numValidos = new ArrayList<int[]>();

		// navega a matriz
		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				char curChar = matriz[x][y];

				if (Character.isDigit(curChar)) {
					int num = curChar - '0';
					int startX = x;
					int endX = x;

					if (x + 1 < xLen) {
						boolean podePegarProximo = true;
						int curX = x + 1;
						char nextChar = matriz[curX][y];

						while (podePegarProximo && Character.isDigit(nextChar)) {
							endX = curX;
							num = num * 10 + (nextChar - '0');

							curX++;
							if (curX < xLen) {
								nextChar = matriz[curX][y];
							} else {
								podePegarProximo = false;
							}
						}

						int[] valores = new int[] { startX, endX, y, num };
						numValidos.add(valores);

						x = endX;
					}

				}

			}
		}
		
		System.out.println("Chegou aqui");

//		for (int[] vals : numValidos) {
//			int start = vals[0];
//			int end = vals[1];
//			int y = vals[2];
//			int num = vals[3];
//
//			System.out
//					.println("Numero " + num + " esta entre" + String.format("(%d, %d) e (%d, %d)", start, y, end, y));
//		}
		int contador = 0;
		
		for (int y = 0; y <yLen; y++) {
			for (int x = 0; x <xLen; x++) {
				char curChar = matriz[x][y];
				if (curChar == '*') {
					List<int[]> tempLista = new ArrayList<int[]>();
					int limEsquerdoX = (x- 1 <= 0) ? x: x - 1;
					int limDireitoX = (x + 1 >= xLen) ? x : x + 1;
					
					if (y - 1 >= 0) {
						tempLista.addAll(pegaNumerosEntre(limEsquerdoX, limDireitoX, y - 1, numValidos));
					}
					
					if (y + 1 < yLen) {
						tempLista.addAll(pegaNumerosEntre(limEsquerdoX, limDireitoX, y + 1, numValidos));
					}
					
					if (limEsquerdoX != x) {
						tempLista.addAll(pegaNumerosEntre(limEsquerdoX, limEsquerdoX, y, numValidos));
					}
					
					if (limDireitoX != x) {
						tempLista.addAll(pegaNumerosEntre(limDireitoX, limDireitoX, y, numValidos));
					}
					
					System.out.print("o asterisco em (" + x + ", " + y + ") esta cercado por ");
					for (int[] val : tempLista) {
						System.out.print(val[3] + " ");
					}
					System.out.println();
					
					if (tempLista.size() == 2) {
						contador += tempLista.get(0)[3] * tempLista.get(1)[3];
					}
					
				}
			}
		}
		
		System.out.println(contador);
		
		

	}
	
	static List<int[]> pegaNumerosEntre(int start, int end, int y, List<int[]> numValidos){
		int i = 0;
		List<int[]> listaReturn = new ArrayList<int[]>();
		
		while(i < numValidos.size() && numValidos.get(i)[2] != y) {
			i++;
		}
		
		while(i < numValidos.size() && numValidos.get(i)[2] == y) {
			int[] num = numValidos.get(i);
			if (!listaReturn.contains(num)) {
				int startX = num[0];
				int endX = num[1];
				
				if ((startX >= start && startX <= end) || (endX <= end && endX >= start) ) {
					listaReturn.add(num);
				}
			}
			i++;
		}
		
		return listaReturn;
	}
}
