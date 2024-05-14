package ui.printer;

import java.util.HashMap;
import java.util.Map;

public class FileLoadMessagePrinter {
    private static final Map<Integer,String> messages;
    static {
        messages = new HashMap<>();
        messages.put(0,"File loaded successfully!");
        messages.put(1,"File not found (or is not .xml)");
        messages.put(2,"More word cards than words! Please try loading a different file.");
        messages.put(3,"More black word cards than black words! Please try loading a different file.");
        messages.put(4,"More team cards than word cards! Please try loading a different file.");
        messages.put(5,"Board dimensions don't fit the number of cards! Please try loading a different file.");
        messages.put(6,"Team names are not unique! Please try loading a different file.");
    }
    public static void print(int errorCode) {
        System.out.println(messages.getOrDefault(errorCode, "Unexpected error"));
    }
}
