package ui;

import java.util.HashMap;
import java.util.Map;

public class FileLoadMessagePrinter {
    private static Map<Integer,String> messages;
    static {
        messages = new HashMap<>();
        messages.put(0,"File loaded successfully");
        messages.put(1,"File not found (or is not .xml)");
        messages.put(2,"More word cards than words");
        messages.put(3,"More black word cards than black words");
        messages.put(4,"More team cards than word cards");
        messages.put(5,"Board dimensions don't fit the number of cards");
        messages.put(6,"Team names are not unique");
    }
    public static void print(int errorCode) {
        if (messages.containsKey(errorCode)) {
            System.out.println(messages.get(errorCode));
        }
        else {
            System.out.println("Unexpected error");
        }
    }
}
