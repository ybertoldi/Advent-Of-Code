package solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.NumsValidator;

public class Day3 {

	public static void main(String[] args) throws FileNotFoundException {
		String PATH = "C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\engine.txt";
		File file = new File(PATH);
		BufferedReader br = new BufferedReader(new FileReader(file));

		NumsValidator validator = new NumsValidator();
		List<Integer> priorOcurrences = new ArrayList<Integer>();
		List<Integer> curOccurences = new ArrayList<Integer>();
		int x, y;
		y = 0;

		for (String line : br.lines().toList()) {
			x = 0;
			for (char c : line.toCharArray()) {
				if (c != '.') {
					if (c >= 48 && c <= 57) {
						validator.add(c, x);
					} else {
						validator.foundSymbolAt(x);
					}
				} else {
					if (validator.isWithNumOpen()) {
						validator.endNum();
					}
				}
				
				x++;
			}
			System.out.println();
			System.out.println("Linha " + (y+1) + ": ");
			validator.newLine();
			y++;
		}
		
		System.out.println(validator.getSum());

	}

}
