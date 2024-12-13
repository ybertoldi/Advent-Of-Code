package year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * O dicionário guarda uma pedra e a o tamanho da lista resultante após piscar essa pedra x vezes
 * p. ex:
 * 
 * dict = {
 * 			125: {
 *					1: 1,
 *					2: 3,
 *					3: 5,
 *					4: 11,
 *					5: 22
 *					... 
 *				 }
 *
 *			52: {
 *					1: 1,
 *					2: 1,
 *					3: 2,
 *					4: 4,
 *					5: 8
 *					... 
 *				 }
 * 			...
 * 
 * 		  }
 * 
 *   Quer dizer:
 * - após 2 piscadas para pedra 125, obtemos uma lista com 3 elementos.
 * - após 3 piscadas para pedra 125, obtemos uma lista com 5 elementos.
 * - após 4 piscadas para pedra 125 obtemos uma lista com 11 elementos.
 * 
 *  Map<> d1 = dict.get(52); => atribui ao mapa d1 os dados sobre a relação piscada-tamanhoDaListaResultante para 52.
 *  d1.get(5); => retorna o tamanho da lista resultante após piscarmos 5 vezes para 52, no caso 8.
 * 
 * 
 */


public class Day11Pt2 {
	public static void main(String[] args) {
		final int VEZES_A_RODAR = 25;
		
		long result = 0;
		
		List<Long> stoneRow = new ArrayList<Long>();
		stoneRow.add(125l);
		stoneRow.add(17l);
		
		Map<Long, Map<Integer, Long>> dict = new HashMap<>();
		
		for (Long stone : stoneRow) {
			result+= blink(stone, VEZES_A_RODAR, dict);
		}
		System.out.println(result);
	}
	
	public static long blink(long stone, int times, Map<Long, Map<Integer, Long>> dict) {
		if (times == 0) {
			return 1;
		}
		
		Map<Integer, Long> d1 = dict.get(stone);
		if (d1 != null) {
			Long e = d1.get(times);
			if (e != null) {
				return e;
			}
		}
		
		String t = "" + stone;
		long e;
		
		if (stone == 0) {
			e = blink(1, times - 1, dict);
		}
		else if (t.length() % 2 == 0) {
			int divisionPoint = t.length() / 2;
			long leftStone = Long.parseLong(t.substring(0,divisionPoint));
			long rightStone = Long.parseLong(t.substring(divisionPoint));
			e = blink(leftStone, times - 1, dict) + blink(rightStone, times - 1, dict);
			
		}
		else {
			e = blink(stone * 2024, times - 1, dict);
		}
		
		if (d1 == null) {
			d1 = new HashMap<Integer, Long>();
			dict.put(stone, d1);
		}
		d1.put(times, e);
		
		return e;
	}
}
