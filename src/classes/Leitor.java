package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leitor {
	
	public static char[][] retornaMatriz(String path) throws IOException{
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		
		List<String> lines = new ArrayList<String>();
		
		int j = 0;
		while ((st = br.readLine()) != null) {
			lines.add(st);
		}
		
		char[][] matrix = new char[lines.size()][lines.get(0).length()];

		for (int i = 0; i < lines.size(); i++) {
			matrix[i] = lines.get(i).toCharArray();
		}
		
		return matrix;
	}
	
	public static List<String> retornaListaLinhas(String path) throws IOException{
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		
		List<String> lines = new ArrayList<String>();
		
		int j = 0;
		while ((st = br.readLine()) != null) {
			lines.add(st);
		}
		
		return lines;
	}
	
	public static void printaMatriz(char[][] matriz) {
		for(int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
	}
}
