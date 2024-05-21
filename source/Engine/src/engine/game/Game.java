package engine.game;

import dto.GameSpec;
import dto.WordCard;

/**
 * Manages the state and logic of a Codenames game session.
 */
public class Game {
    private final WordCard[] deck;              // Array of WordCard objects representing the game board
    private final int[] team_score;             // Array tracking the score for each team
    private final int[] team_turn_count;        // Array tracking the number of turns taken by each team
    private int curr_team_i = 0;                // Index of the current team whose turn it is
    private int winner = -1;                    // Index of the winning team, -1 if no winner yet
    private boolean gameOver = false;           // Flag to indicate if the game is over

    /**
     * Constructs a Game instance initialized with the specifications provided by GameSpec.
     *
     * @param spec A GameSpec object containing the settings and data needed to initialize the game.
     */
    public Game(GameSpec spec) {
        deck = WordCardFactory.initializeDeck(spec); // Initialize the deck based on the game specification
        int teamNum = spec.getTeam_names().length;   // Number of teams
        team_score = new int[teamNum];               // Initialize the score array for each team
        team_turn_count = new int[teamNum];          // Initialize the turn count array for each team
    }

    /**
     * Sets the index of the winning team.
     *
     * @param winner Index of the winning team.
     */
    public void setWinner(int winner) { this.winner = winner; }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() { return gameOver; }

    /**
     * Sets the game as over.
     */
    public void setGameOver() { this.gameOver = true; }

    /**
     * Increments the turn index for the current team, looping back to the first team if necessary.
     */
    public void increment_curr_team_i() {
        team_turn_count[curr_team_i++]++;
        if (curr_team_i >= team_score.length) {
            curr_team_i = 0; // Reset to the first team if the end is reached
        }
    }

    // Getter methods for all properties with straightforward documentation
    public WordCard[] getDeck() { return deck; }
    public int[] getTeam_score() { return team_score; }
    public int[] getTeam_turn_count() { return team_turn_count; }
    public int getWinner() { return winner; }
    public int getCurr_team_i() { return curr_team_i; }
}

