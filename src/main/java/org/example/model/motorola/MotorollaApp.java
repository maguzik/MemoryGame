package org.example.model.motorola;

import java.util.Map;
import java.util.Scanner;

public class MotorollaApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printInstruction();
        String usersChoose = scanner.nextLine();
        System.out.println(usersChoose);
        DifficultLevel.Description description = DifficultLevel.Description.fromString(usersChoose);
        DifficultLevel difficultLevel = new DifficultLevel(description);
        System.out.println(difficultLevel);

        Board board = new Board(difficultLevel);

        while (!board.isGameOver()) {
            printBoard(board);
            String userInput = null;


            while (!board.getAllowedLocation().contains(userInput)) {
                userInput = scanner.nextLine();

            }
            board.choseCardByLocation(userInput);

        }
    }

    private static void printBoard(Board board) {
        int columns = 0;
        Map<Integer, String> rowSymbols = Map.of(1, "A ", 2, "B ");
        if (board.getCards().size() == 8) {
            columns = 5;
            System.out.println("  1 2 3 4");

        }
        if (board.getCards().size() == 16) {
            columns = 9;
            System.out.println("  1 2 3 4 5 6 7 8");
        }
        for (int i = 0; i < 2; i++) {

            for (int n = 0; n < columns; n++) {
                if (n == 0) {
                    System.out.print(rowSymbols.get(i + 1));
                } else {
                    int finalI = i;
                    int finalN = n;
                    String printValue = board.getCards().stream()
                            .filter(card -> card.getLocation().equals(new Location(finalI + 1, finalN)))
                            .findFirst()
                            .orElseThrow()
                            .getPrintValue();
                    System.out.print(printValue + " ");
                }

            }
            System.out.println();
        }
    }

    private static void printInstruction() {
        System.out.println("Choose between two difficulty levels. Easy - \n" +
                "program loads 4 randomly selected words to discover and player has 10 chances to reveal all memory, \n" +
                "or Hard - user needs to guess location of 8 randomly \n" +
                "selected word pairs and has 15 chances to reveal all word pairs.\n" +
                "Write Easy or Hard. ");
    }

}
