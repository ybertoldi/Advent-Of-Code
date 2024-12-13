package year2024;

import java.io.IOException;
import java.util.Scanner;

import classes.Leitor;

public class Day6Pt1 {
	public static void main(String[] args) throws IOException {
		final String PATH = "C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\GuardPatrol.txt";
		char[][] matriz = Leitor.retornaMatriz(PATH);
		
		int[] gPos = new int[2];
		final int[][] directions = new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}};
		final char[] gFigs = new char[] {'^', '>', 'V', '<'};
		int dirIndex = 0;

		int[] curDir = directions[dirIndex];
		char curFig = gFigs[dirIndex];
		
		Scanner s = new Scanner(System.in);
		
		//acha a posicao do guarda
		boolean canBreak = false;
		for (int l = 0; l < matriz.length; l++) {
			for (int c = 0; c < matriz[0].length; c++) {
				if (matriz[l][c] == '^') {
					gPos[0] = l;
					gPos[1] = c;
					canBreak = true;
					break;
				}
			}
			if (canBreak)
				break;
		}
		
		int lEnd = matriz.length;
		int cEnd = matriz[0].length;
		int counter = 0;
		while (true) {
			//ve se o boneco está pra sair da matriz
			if (gPos[0] <= 0 || gPos[0] >= lEnd - 1 || gPos[1] <= 0 || gPos[1] >= cEnd - 1) {
				counter++;
				break;
			}
			
			//ve se o boneco bateu na parede
			if (matriz[ gPos[0] + curDir[0]][ gPos[1] + curDir[1] ] == '#') 
			{
				dirIndex = (dirIndex + 1 >= 4)? 0 : dirIndex + 1;
				curDir = directions[dirIndex];
				curFig = gFigs[dirIndex];
				matriz[gPos[0]][gPos[1]] = curFig;
			}
			//movimenta o boneco
			else 
			{
				matriz[gPos[0]][gPos[1]] = 'X'; 
				
				gPos[0] += curDir[0];
				gPos[1] += curDir[1];
				if (matriz[gPos[0]][gPos[1]] != 'X') {
					counter++;
				}
				matriz[gPos[0]][gPos[1]] = curFig;
			}
			

			Leitor.printaMatriz(matriz);
			s.nextLine();
			
		}
		
		System.out.println(counter);
	}
}
