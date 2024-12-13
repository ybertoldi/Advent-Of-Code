package year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * O dicionário guarda uma pedra e o tamanho da lista resultante após piscar para essa pedra x vezes.
 * p. ex:
 * 
 * dict = {
 * 			125: {
 *					1: 1,
 *					2: 3,
 *					3: 5,
 *					4: 11,
 *					5: 22,
 *					... 
 *				 },
 *
 *			52: {
 *					1: 1,
 *					2: 1,
 *					3: 2,
 *					4: 4,
 *					5: 8,
 *					... 
 *				 },
 * 			...
 * 
 * 		  }
 * 
 *   Quer dizer:
 * - após 2 piscadas para pedra 125, obtemos uma lista com 3 elementos.
 * - após 3 piscadas para pedra 125, obtemos uma lista com 5 elementos.
 * - após 4 piscadas para pedra 125 obtemos uma lista com 11 elementos.
 * - ...
 * 
 *  Map<> d1 = dict.get(52); => atribui ao mapa d1 os dados sobre a relação piscada-tamanhoDaListaResultante para 52.
 *  d1.get(5); => retorna o tamanho da lista resultante após piscarmos 5 vezes para 52, no caso 8.
 * 
 * 
 */


public class Day11Pt2 {
	public static void main(String[] args) {
		final String INPUT= "4022724 951333 0 21633 5857 97 702 6";
		final int PISCADAS = 75;
		
		List<Long> pedras = new ArrayList<Long>();
		for (String s : INPUT.split(" ")) {
			pedras.add(Long.parseLong(s));
		}
		Map<Long, Map<Integer, Long>> mapa = new HashMap<Long, Map<Integer,Long>>();
		
		long total = 0;		
		for (long pedra: pedras) {
			total += pedrasResultantes(pedra, PISCADAS, mapa);
		}
		
		System.out.println(total);
		
	}

	private static long pedrasResultantes(long pedra, int piscadas, Map<Long, Map<Integer, Long>> mapa) {
		if (piscadas == 0) {
			return 1;
		}
		
		long res;
		Map<Integer, Long> mapaPedra = mapa.get(pedra);
		
		if (mapaPedra != null) {
			Long val = mapaPedra.get(piscadas);
			if (val != null) {
				return val;
			}
		}
		
		String s = "" + pedra;
		
		if (s.equals("0")) {
			res =  pedrasResultantes(1, piscadas - 1, mapa);
		}
		else if (s.length() % 2 == 0) {
			int div = s.length() / 2;
			long esquerda =  Long.parseLong(s.substring(0, div));
			long direita =  Long.parseLong(s.substring(div));
			
			res = pedrasResultantes(esquerda, piscadas - 1, mapa) + pedrasResultantes(direita, piscadas - 1, mapa);						
		}
		else {
			res = pedrasResultantes(pedra * 2024, piscadas - 1, mapa);
		}
		
		if (mapaPedra == null) {
			mapaPedra = new HashMap<Integer, Long>();
			mapa.put(pedra, mapaPedra);
		}
		mapaPedra.put(piscadas, res);
		
		return res;
		
	}
}
