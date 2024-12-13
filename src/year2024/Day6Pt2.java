package year2024;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import classes.Leitor;

public class Day6Pt2 {
	public static void main(String[] args) throws IOException {
		final String PATH = "C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\GuardPatrol.txt";
		char[][] matriz = Leitor.retornaMatriz(PATH);

		int[] gPos = new int[2];
		int[][] directions = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		char[] gFigs = new char[] { '^', '>', 'V', '<' };
		char[] dFigs = new char[] { '↑', '→', '↓', '←' };
		int dirIndex = 0;

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
		int[] curDir = directions[dirIndex];
		char curFig = gFigs[dirIndex];

		HashMap<Integer, List<int[]>> turningPoints = new HashMap<Integer, List<int[]>>();

		turningPoints.put(0, new ArrayList<int[]>());
		turningPoints.put(1, new ArrayList<int[]>());
		turningPoints.put(2, new ArrayList<int[]>());
		turningPoints.put(3, new ArrayList<int[]>());
		turningPoints.get(0).add(new int[] { gPos[0], gPos[1] });

		int tempIndex = 1;
		int tempDir;
		int[] tempG;
		List<int[]> pointsToSearch = null;
		int[] searchPoint = null;
		int coordToCheck = 0;

		while (true) {

			if (gPos[0] <= 0 || gPos[0] >= lEnd - 1 || gPos[1] <= 0 || gPos[1] >= cEnd - 1) {
				break;
			}

			if (matriz[gPos[0] + curDir[0]][gPos[1] + curDir[1]] == '#') {
				dirIndex = (dirIndex + 1 >= 4) ? 0 : dirIndex + 1;
				curDir = directions[dirIndex];
				curFig = gFigs[dirIndex];
				matriz[gPos[0]][gPos[1]] = dFigs[dirIndex];

				turningPoints.get(dirIndex).add(new int[] { gPos[0], gPos[1] });
				tempIndex = (dirIndex + 1 >= 4) ? 0 : dirIndex + 1;
				pointsToSearch = turningPoints.get(tempIndex);
				searchPoint = getNextSearchPoint(pointsToSearch, gPos, dirIndex);
				coordToCheck = (dirIndex % 2 == 0)? 0:1;
			}

			else {
				if (matriz[gPos[0]][gPos[1]] == curFig) {
					matriz[gPos[0]][gPos[1]] = '.';
				}

				gPos[0] += curDir[0];
				gPos[1] += curDir[1];
				if (searchPoint != null) {
					if (gPos[coordToCheck] == searchPoint[coordToCheck] ) {
						if (canGo(gPos, searchPoint, directions[tempIndex], matriz)) {
							counter++;
						}
						searchPoint = getNextSearchPoint(pointsToSearch, gPos, dirIndex);
					}
				}
				

				if (matriz[gPos[0]][gPos[1]] == '.')
					matriz[gPos[0]][gPos[1]] = curFig;
			}
			Leitor.printaMatriz(matriz);
			System.out.println();
		}

		System.out.println(counter);
		Leitor.printaMatriz(matriz);
	}



	private static int[] getNextSearchPoint(List<int[]> pointsToSearch, int[] gPos, int dirIndex) {
		if (pointsToSearch.size() == 0) {
			return null;
		}
		
		int[] searchPoint = pointsToSearch.get(0);
		boolean found = false;
		int coord = (dirIndex % 2 == 0)? 0:1;
		boolean positive = (dirIndex == 0 || dirIndex == 1)? true: false;
		
		for (int[] point : pointsToSearch) {
			if (positive) {
				if (point[coord] > gPos[coord] ) {
					if (point[coord] < searchPoint[coord])
						searchPoint = point;
					
					found = true;
				}
			}
			else {
				if (point[coord] < gPos[coord]) {
					if (point[coord] > searchPoint[coord]) {
						searchPoint = point;
					}
					found = true;
				}
			}
		}
		
		if (!found)
			return null;
		return searchPoint;
	}
	
	private static boolean canGo(int[] gPos, int[] searchPoint, int[] tempDir, char[][] matriz) {
		int[] temp = Arrays.copyOf(gPos, 2);
		while(temp[0] != searchPoint[0] || temp[1] != searchPoint[1]) {
			if (matriz[temp[0] + tempDir[0]][temp[1] + tempDir[1]] == '#') {
				return false;
			}	
		}
		
		return true;
	}
	
}
