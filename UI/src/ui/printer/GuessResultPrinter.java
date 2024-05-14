package ui.printer;

import java.util.HashMap;
import java.util.Map;

public class GuessResultPrinter {
    private static final Map<Integer,String> messages;
    static {
        messages = new HashMap<>();
        messages.put(-1,"Invalid card number, please try again.");
        messages.put(0,"Word already guessed, please try again.");
        messages.put(1,"Correct!");
        messages.put(2,"Opposing team word!");
        messages.put(3,"Black word!");
        messages.put(4,"Neutral word!");
    }
    public static void print(int result) {
        System.out.println(messages.getOrDefault(result, "Invalid guess"));
    }
}
