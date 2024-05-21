package ui.printer;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for printing messages related to the outcome of a player's guess in the game.
 * Maps various guess results to specific messages and displays them to provide feedback to the player.
 */
public class GuessResultPrinter {
    // Map of guess results to messages, initialized statically for use across all instances.
    private static final Map<Integer, String> messages;
    static {
        messages = new HashMap<>();
        messages.put(-1, "Invalid card number, please try again.");
        messages.put(0, "Word already guessed, please try again.");
        messages.put(1, "Correct!");
        messages.put(2, "Opposing team word!");
        messages.put(3, "Black word!");
        messages.put(4, "Neutral word!");
    }

    /**
     * Prints a message based on the provided result of a player's guess.
     * If no message is found for the given result, it prints an "Invalid guess" message.
     *
     * @param result The result code of the guess for which a message should be printed.
     */
    public static void print(int result) {
        // Retrieve and print the message corresponding to the result.
        // If result is not found, print a default message.
        System.out.println(messages.getOrDefault(result, "Invalid guess"));
    }
}

