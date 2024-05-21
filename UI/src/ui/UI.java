package ui;

import java.util.Scanner;

/**
 * Defines the user interface contract for managing interactions within the game.
 * This interface outlines methods required for displaying menus, handling game configurations,
 * and managing gameplay based on user input.
 */
public interface UI {

    /**
     * Displays the main menu and handles user input to navigate various game options.
     *
     * @param scanner The Scanner object used to read user input from the console.
     */
    public void goToMenu(Scanner scanner);

    /**
     * Prompts the user for a file path and sends it to the game engine to load game specifications.
     *
     * @param scanner The Scanner object used to read the file path input from the user.
     */
    public void sendFilePathToEngine(Scanner scanner);

    /**
     * Displays the current game specifications such as card count, board dimensions, and team details.
     */
    public void displayGameSpec();

    /**
     * Starts a new game based on the loaded game specifications.
     */
    public void startNewGame();

    /**
     * Processes a player's turn by handling actions such as making guesses or choosing to end the turn.
     *
     * @param scanner The Scanner object used to read user input during the turn.
     */
    public void makeTurn(Scanner scanner);

    /**
     * Displays the current state of the game, optionally hiding certain information based on the game context.
     *
     * @param hidden If true, sensitive game information like card identity may be hidden; otherwise, all information is displayed.
     */
    public void displayGame(boolean hidden);

    /**
     * Terminates the current game session and exits the application.
     */
    public void exitGame();
}

