package ui;

import engine.WordCard;

public class BoardPrinter {
    public static void displayBoard(WordCard[] deck, int rows, int columns) {
        int cardIndex = 0;
        int maxWidth = calculateMaxWidth(deck) + 10;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (cardIndex < deck.length) {
                    WordCard card = deck[cardIndex++];
                    System.out.printf("%-" + maxWidth + "s", card.getWord());
                }
            }
            System.out.println();
            cardIndex -= columns;
            for (int col = 0; col < columns; col++) {
                if (cardIndex < deck.length) {
                    WordCard card = deck[cardIndex++];
                    String status = card.isFlag() ? "V" : "X";
                    System.out.printf("%-" + maxWidth + "s", String.format("[%d]%s(%s)", cardIndex, status, card.getTeam()));
                }
            }
            System.out.println();
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
