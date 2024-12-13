package year2024;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import classes.Leitor;

public class Day7Pt1 {
	public static void main(String[] args) throws IOException {
		String PATH = "C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\2023_07.txt";
		List<String> linhas = Leitor.retornaListaLinhas(PATH);
		
		for (String linha : linhas) {
			int targetNum = Integer.valueOf(linha.split(":")[0]);
			int nums[] = Stream.of(linha.split(":")[1].strip().split("[ ]"))
					  .mapToInt(Integer::parseInt)
					  .toArray();
			
			System.out.println(targetNum);
			System.out.println(Arrays.toString(nums));
			System.out.println();			
		}
	}
}
