package ui.printer;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a centralized way to print messages related to file loading outcomes.
 * This class maps error codes to user-friendly messages and displays them accordingly.
 */
public class FileLoadMessagePrinter {
    // Map of error codes to messages, initialized statically for use across all instances.
    private static final Map<Integer, String> messages;
    static {
        messages = new HashMap<>();
        messages.put(0, "File loaded successfully!");
        messages.put(1, "File not found (or is not .xml)");
        messages.put(2, "More word cards than words! Please try loading a different file.");
        messages.put(3, "More black word cards than black words! Please try loading a different file.");
        messages.put(4, "More team cards than word cards! Please try loading a different file.");
        messages.put(5, "Board dimensions don't fit the number of cards! Please try loading a different file.");
        messages.put(6, "Team names are not unique! Please try loading a different file.");
    }

    /**
     * Prints a message based on the provided error code.
     * If no message is found for the given code, it prints an "Unexpected error" message.
     *
     * @param errorCode The error code for which a message should be printed.
     */
    public static void print(int errorCode) {
        // Retrieve and print the message corresponding to the errorCode.
        // If errorCode is not found, print a default message.
        System.out.println(messages.getOrDefault(errorCode, "Unexpected error"));
    }
}

