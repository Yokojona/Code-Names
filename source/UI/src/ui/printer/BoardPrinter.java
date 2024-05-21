package ui.printer;

import dto.WordCard;

/**
 * Utility class for printing the game board in a structured and formatted manner.
 */
public class BoardPrinter {
    private static final int gap = 8; // the space between word cards.

    /**
     * Prints the game board to the console, formatting it according to specified dimensions and visibility settings.
     *
     * @param deck The array of WordCard objects representing the cards on the board.
     * @param rows The number of rows in the board layout.
     * @param columns The number of columns in the board layout.
     * @param hidden Determines if the board should hide certain information. If true, only revealed cards show full details.
     */
    public static void print(WordCard[] deck, int rows, int columns, boolean hidden) {
        int cardI = 0;
        int maxWidth = calculateMaxWidth(deck) + gap;  // Calculate the maximum width for uniform column width.

        for (int row = 0; row < rows; row++) {
            // Print each word on the board with adjusted spacing to maintain column alignment.
            for (int col = 0; col < columns; col++) {
                if (cardI < deck.length) {
                    WordCard card = deck[cardI++];
                    System.out.printf("%-" + maxWidth + "s", card.getWord());
                }
            }
            System.out.println();  // New line after each row of words
            cardI -= columns;       // Reset card index to the start of the current row to print details below.

            // Print details under each word based on the visibility setting.
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
                    } else
                        System.out.printf("%-" + maxWidth + "s", String.format("[%d]%s%s", cardI, status, team));
                }
            }
            System.out.println("\n"); // Extra line after each row for better readability
        }
    }

    /**
     * Calculates the maximum width of word strings in the deck to set column widths uniformly.
     *
     * @param deck Array of WordCard objects.
     * @return The maximum length found among word strings in the deck.
     */
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

