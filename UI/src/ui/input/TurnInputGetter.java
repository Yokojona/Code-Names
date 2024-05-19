package ui.input;

import ui.printer.BoardPrinter;
import ui.printer.GuessResultPrinter;

import java.util.Scanner;

public class TurnInputGetter {
    static public int numGuesses(Scanner scanner, int max) {
        int numGuesses = 0;
        boolean flag = false;
        System.out.println("Enter number of relevant words (or 0 to return):");
        while (!flag) {
            if (scanner.hasNextInt()) {
                numGuesses = scanner.nextInt();
                if (numGuesses < 0 || numGuesses > max) {
                    System.out.println("invalid number of relevant words");
                    scanner.nextLine();
                } else {
                    flag = true;
                }
            } else {
                System.out.println("Only numbers please");
                scanner.nextLine();
            }
        }
        return numGuesses;
    }

    static public int guess(Scanner scanner) {
        int guess = 0;
        boolean flag = false;
        System.out.println("Pick a card number (or 0 to end turn):");
        while (!flag) {
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                flag = true;
            } else {
                System.out.println("Only numbers please, try again");
                scanner.nextLine();
            }
        }
        return guess;
    }
}

