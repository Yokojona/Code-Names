package ui.main;

import ui.UI;
import ui.UIImp;

import java.util.Scanner;

/**
 * The main entry point of the game application.
 * This class initializes the user interface and manages the main loop for user interactions.
 */
public class Main {

    /**
     * Initializes the game interface and continuously processes user input through the main menu.
     *
     * @param args Command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        UI ui = new UIImp(); // Create an instance of the UI implementation.
        Scanner scanner = new Scanner(System.in); // Scanner object to read user input from the console.

        while (true) {
            ui.goToMenu(scanner); // Display the main menu and handle user interaction repeatedly.
        }
    }
}
