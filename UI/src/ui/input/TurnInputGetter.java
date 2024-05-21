package ui.input;

import java.util.Scanner;

/**
 * Provides methods for obtaining input from the user during a game turn.
 * This class handles the process of collecting guesses and determining the number of relevant words for a turn.
 */
public class TurnInputGetter {

    /**
     * Prompts the user to enter the number of relevant words for their guess, ensuring it is within a valid range.
     *
     * This method asks the player to specify how many words they believe are relevant to their current turn's guess,
     * ensuring that the input is a valid integer and within the specified maximum limit.
     *
     * @param scanner The Scanner object used to read input from the console.
     * @param max The maximum number of guesses that are allowed (usually based on game rules).
     * @return The number of relevant words the user has entered, validated to be within the allowed range.
     */
    static public int numGuesses(Scanner scanner, int max) {
        int numGuesses = 0;
        boolean flag = false;
        System.out.println("Enter number of relevant words (or 0 to return):");
        while (!flag) {
            if (scanner.hasNextInt()) {
                numGuesses = scanner.nextInt();
                if (numGuesses < 0 || numGuesses > max) {
                    System.out.println("Invalid number of relevant words");
                    scanner.nextLine();  // Clear buffer to handle next input correctly
                } else {
                    flag = true;  // Valid input, exit loop
                }
            } else {
                System.out.println("Only numbers please");
                scanner.nextLine();  // Clear buffer to handle next input correctly
            }
        }
        return numGuesses;
    }

    /**
     * Prompts the user to pick a card number for their guess.
     * <p>
     * This method ensures that the user inputs a valid integer, which represents the card number they want to guess.
     * It continues to prompt until a valid number is entered.
     *
     * @param scanner The Scanner object used to read input from the console.
     * @return The card number that the user has chosen.
     */
    static public int guess(Scanner scanner) {
        int guess = 0;
        boolean flag = false;
        System.out.println("Pick a card number (or 0 to end turn):");
        while (!flag) {
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                flag = true;  // Assume any integer input is valid (validation of card range should be handled elsewhere)
            } else {
                System.out.println("Only numbers please, try again");
                scanner.nextLine();  // Clear buffer to handle next input correctly
            }
        }
        return guess;
    }
}


