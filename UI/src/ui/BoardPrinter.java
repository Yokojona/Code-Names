package ui;

import engine.WordCard;

public class BoardPrinter {
    public static void displayBoard(WordCard[] deck, int rows, int columns, boolean hidden) {
        int cardI = 0;
        int maxWidth = calculateMaxWidth(deck) + 8;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (cardI < deck.length) {
                    WordCard card = deck[cardI++];
                    System.out.printf("%-" + maxWidth + "s", card.getWord());
                }
            }
            System.out.println();
            cardI -= columns;
            for (int col = 0; col < columns; col++) {
                if (cardI < deck.length) {
                    WordCard card = deck[cardI++];
                    String status = card.isFlag() ? "V" : "X";
                    String team = (card.getTeam() == null) ? "" : "(" + card.getTeam() + ")";
                    if (hidden) {
                        if (status.equals("V"))
                            System.out.printf("%-" + maxWidth + "s", String.format("[%d]%s%s", cardI, status, team));
                        else
                            System.out.printf("%-" + maxWidth + "s", String.format("[%d]", cardI));
                    }
                    else
                        System.out.printf("%-" + maxWidth + "s", String.format("[%d]%s%s", cardI, status, team));
                }
            }
            System.out.println("\n");
        }
    }

    private static int calculateMaxWidth(WordCard[] deck) {
        int max = 0;
        for (WordCard card : deck) {
            if (card.getWord().length() > max) {
                max = card.getWord().length();
            }
        }
        return max;
    }
}
