package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day4 {

	public static void main(String[] args) throws FileNotFoundException {
		String PATH = "C:\\Users\\Yuri\\OneDrive\\Área de Trabalho\\advent of code\\src\\resources\\tickets.txt";
		File file = new File(PATH);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);

		HashMap<String, List<String>> cardWins = new HashMap<String, List<String>>();

		while (sc.hasNextLine()) {

			String txt = sc.nextLine();
			String[] values = txt.split(": ")[1].split("[|]");
			String[] winningValues = values[0].split(" ");
			String[] myValues = values[1].split(" ");

			String[] cardArr = txt.split(": ")[0].split(" ");
			String card = cardArr[cardArr.length - 1];
			int cardNum = Integer.valueOf(card);

			int points = 0;

			for (String myVal : myValues) {
				if (!myVal.equals("")) {
					if (Arrays.asList(winningValues).contains(myVal)) {
						points++;
					}
				}
			}

			List<String> wins = new ArrayList<String>();
			for (int i = 1; i < points; i++) {
				wins.add((cardNum + i) + "");
			}

			cardWins.put(card, wins);
		}

		List<String> cardsToSearch = new ArrayList<String>();
		for (int i = 1; i <= 211; i++) {
			cardsToSearch.add(i + "");
		}

		HashMap<String, Integer> cardsObtained = new HashMap<String, Integer>();
		int count = 211;

		while (cardsToSearch.size() > 0) {
			String curCard = cardsToSearch.get(cardsToSearch.size() - 1);

			int tempCounter = cardWins.get(curCard).size();
			for (String card : cardWins.get(curCard)) {
				tempCounter += cardsObtained.get(card);
			}
			cardsObtained.put(curCard, tempCounter);
			count += tempCounter;
			System.out.println(
					"card " + curCard + " wins " + tempCounter + " other cards  -  " + cardWins.get(curCard));

			cardsToSearch.remove(cardsToSearch.size() - 1);

		}

		System.out.println(count);

		System.out.println(
				"---------------------------------------------------------------------------------------------------");
		List<String> testCardToSearch = new ArrayList<String>();
		testCardToSearch.add("1");
		testCardToSearch.add("2");
		testCardToSearch.add("3");
		testCardToSearch.add("4");
		testCardToSearch.add("5");
		testCardToSearch.add("6");

		HashMap<String, List<String>> testCardsWins = new HashMap<String, List<String>>();
		testCardsWins.put("1", Arrays.asList("2", "3", "4", "5"));
		testCardsWins.put("2", Arrays.asList("3", "4"));
		testCardsWins.put("3", Arrays.asList("4", "5"));
		testCardsWins.put("4", Arrays.asList("5"));
		testCardsWins.put("5", new ArrayList<String>());
		testCardsWins.put("6", new ArrayList<String>());

		HashMap<String, Integer> testCardsObtained = new HashMap<String, Integer>();

		int testCounter = testCardToSearch.size();

		while (testCardToSearch.size() > 0) {
			String curCard = testCardToSearch.get(testCardToSearch.size() - 1);

			int tempCounter = testCardsWins.get(curCard).size();
			for (String card : testCardsWins.get(curCard)) {
				tempCounter += testCardsObtained.get(card);
			}
			testCardsObtained.put(curCard, tempCounter);
			testCounter += tempCounter;
			System.out.println(
					"card " + curCard + " wins " + tempCounter + " other cards  -  " + testCardsWins.get(curCard));

			testCardToSearch.remove(testCardToSearch.size() - 1);

		}

		System.out.println("test cards: " + testCounter);

		int differentCounter = 211;
		for (int val : cardsObtained.values()) {
			differentCounter += val;
		}
		
		System.out.println("txt cards: " + count);
		System.out.println("txt cards: " + differentCounter);
	}
}
