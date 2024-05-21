package ui.printer;

import engine.Engine;

/**
 * Utility class for printing the main menu of the game.
 * This class interacts with the game engine to display appropriate game options based on the current game state.
 */
public class MenuPrinter {

    /**
     * Prints the main game menu, dynamically showing options based on the current state of the game.
     * <p>
     * The menu options are:
     * (1) Load XML - Load a new game configuration.
     * (2) Game Spec - Display the current game specifications.
     * (3) New Game - Start a new game based on the loaded specifications.
     * (4) Make Turn - Make a turn in the game; this option shows which team's turn is next if a game is active.
     * (5) Game Status - Show the current status of the game.
     * (6) Exit - Exit the game application.
     *
     * @param e The game engine that provides the current state and game data necessary to display the menu.
     */
    public static void print(Engine e) {
        System.out.println("(1) Load XML");
        System.out.println("(2) Game Spec");
        System.out.println("(3) New Game");

        // Check if a game is currently active to provide contextual menu options
        if (e.getGame() != null) {
            // Display which team's turn is next if a game is ongoing
            System.out.println("(4) Make Turn " + "(" +
                    e.getGameSpec().getTeam_names()[e.getGame().getCurr_team_i()] + ")");
        } else {
            System.out.println("(4) Make Turn");  // General option if no game is active
        }

        System.out.println("(5) Game Status");
        System.out.println("(6) Exit");
    }
}
