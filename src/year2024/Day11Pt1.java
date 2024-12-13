package year2024;

import java.util.ArrayList;
import java.util.Arrays;

public class Part_01 {
	public static void main(String[] args) {

		ArrayList<String> init = new ArrayList<String>();
		//String s = "5 89749 6061 43 867 1965860 0 206250";
		//String s = "4022724 951333 0 21633 5857 97 702 6";
		//String s = "125 17";
		init.addAll(Arrays.asList(s.split(" ")));

		ArrayList<String> array = new ArrayList<String>();


		for (int a = 0; a < 25; a++) {
			array.clear();

			for (String value : init) {

				if (value.equals("0")) {
					array.add("1");
				}
				else if (value.equals("1")) {
					array.add("2024");
				}

				else if (value.length() % 2 == 0) {


					int halfPos = value.length() / 2;
					array.add(value.substring(0, halfPos));

					String lastHalf = value.substring(halfPos, value.length());

					array.add(retornaLadoEsquerdoStringFormatada(lastHalf));
				}
				else {
					long mult = Integer.valueOf(value) * 2024;
					String curVal = Math.abs(mult) + "";
					array.add(curVal);
				}
			}
			init.clear();
			for (int b = 0; b < array.size(); b++) {
				init.add(array.get(b));
			}
		}

		System.out.println("Tamanho do array: " + array.size());
		//System.out.println(array);
	}


	public static String retornaLadoEsquerdoStringFormatada(String string) {

		int countZerosRightToLeft = -1;

		String[] vec = string.split("");

		boolean containZero = false;
		for(int i = vec.length-1; i >= 0; i--) {
			
			if (vec[i].equals("0")) {
				countZerosRightToLeft++;
				containZero = true;
			}
			else {
				break;
			}
		}

		if (containZero) {
			String format = string.substring(0, vec.length - countZerosRightToLeft);
			long valueString = Integer.valueOf(format);
			format = valueString + "";
			
			return format;
		}
		else {
			long valueString_2 = Integer.valueOf(string);
			string = valueString_2 + "";
			return string;
		}

	}
}
