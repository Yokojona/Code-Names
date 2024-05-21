package dto;

/**
 * Represents the specifications for a game setup in Codenames.
 * This class holds all the necessary settings and words used to initialize a game board.
 */
public class GameSpec {
    private final String file_path;       // Path to the XML file from which the game settings are loaded
    private final String[] game_words;    // Array containing all the game words
    private final String[] black_words;   // Array containing all the black words (special words in the game)
    private final int cards_count;        // Total number of word cards in the game
    private final int black_cards_count;  // Number of black word cards
    private final int rows;               // Number of rows in the game board
    private final int columns;            // Number of columns in the game board
    private final String[] team_names;    // Array of team names participating in the game
    private final int[] team_cards_count; // Array of integers representing the number of cards each team has

    /**
     * Constructs a GameSpec object with all necessary data for game setup.
     *
     * @param file_path Path to the XML file that specifies the game setup.
     * @param game_words Array of strings representing the words used for gameplay.
     * @param black_words Array of strings representing special black words for gameplay.
     * @param cards_count Total number of word cards.
     * @param black_cards_count Total number of black word cards.
     * @param rows Number of rows in the game board.
     * @param columns Number of columns in the game board.
     * @param team_names Array of team names.
     * @param team_cards_count Array of the count of cards each team is assigned.
     */
    public GameSpec(String file_path, String[] game_words, String[] black_words,
                    int cards_count, int black_cards_count, int rows, int columns,
                    String[] team_names, int[] team_cards_count) {
        this.file_path = file_path;
        this.game_words = game_words;
        this.black_words = black_words;
        this.cards_count = cards_count;
        this.black_cards_count = black_cards_count;
        this.rows = rows;
        this.columns = columns;
        this.team_names = team_names;
        this.team_cards_count = team_cards_count;
    }

    // Getter methods for all properties with straightforward documentation
    public String getFile_path() { return file_path; }
    public String[] getGame_words() { return game_words; }
    public String[] getBlack_words() { return black_words; }
    public int getCards_count() { return cards_count; }
    public int getBlack_cards_count() { return black_cards_count; }
    public int getRows() { return rows; }
    public int getColumns() { return columns; }
    public String[] getTeam_names() { return team_names; }
    public int[] getTeam_cards_count() { return team_cards_count; }
}

