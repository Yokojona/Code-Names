package ui.printer;

import dto.GameSpec;

/**
 * Handles the printing of the current game specifications to the console.
 * This utility class provides a clear display of the configuration details of the game,
 * including card counts, team information, and game dimensions.
 */
public class SpecPrinter {

    /**
     * Prints the details of the provided GameSpec object.
     * If the spec object is null, it outputs a message indicating that the game specifications are not available.
     *
     * @param spec The GameSpec object containing the details to be printed.
     */
    public static void print(GameSpec spec) {
        if (spec != null) {
            System.out.println("Game Spec:");
            System.out.println("file path: " + spec.getFile_path());  // Path to the game configuration file.
            System.out.println("card count: " + spec.getCards_count());  // Total number of cards in the game.
            System.out.println("black card count: " + spec.getBlack_cards_count());  // Number of black cards.
            System.out.println("dimensions: " + spec.getRows() + "x" + spec.getColumns());  // Dimensions of the game board.

            // Print each team's name and the number of cards assigned to them.
            for (int i = 0; i < spec.getTeam_names().length; i++) {
                System.out.println(spec.getTeam_names()[i] + ": " + spec.getTeam_cards_count()[i] + " cards");
            }
        } else {
            System.out.println("Game Spec not found");  // Message if the spec is null, indicating no data is loaded.
        }
    }
}
