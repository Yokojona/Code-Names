package ui.printer;

/**
 * Provides functionality to print the scores and status of each team participating in the game.
 * This class outputs the number of turns taken, the score of each team, and identifies the winner if applicable.
 */
public class ScoresPrinter {

    /**
     * Prints the scores, turn counts, and the winning status for each team.
     * <p>
     * This method iterates through each team and displays:
     * - Team name
     * - Number of turns taken by the team
     * - Current score of the team out of the total card count they are aiming to guess
     * - Indicates if the team is the winner
     *
     * @param teamNames Array of team names.
     * @param cardCount Array of counts of cards each team is supposed to find.
     * @param teamScores Array of scores representing how many cards each team has currently guessed correctly.
     * @param teamTurnCount Array of counts of turns each team has taken.
     * @param winner Index of the winning team. If no team has won yet, this could be set to a value outside team indices.
     */
    static public void print(String[] teamNames, int[] cardCount, int[] teamScores, int[] teamTurnCount, int winner) {
        for (int i = 0 ; i < teamNames.length; i++) {
            int score = teamScores[i]; // Current score for the team
            // Check if the current team is the winner and format the output accordingly
            if (winner == i) {
                System.out.println(teamNames[i] + " (" + teamTurnCount[i] + " turns): " + score
                        + "/" + cardCount[i] + " - Winner!");
            } else {
                System.out.println(teamNames[i] + " (" + teamTurnCount[i] + " turns): " + score
                        + "/" + cardCount[i]);
            }
        }
    }
}

