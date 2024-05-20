package dto;

/**
 * Represents a single card in the Codenames game, containing a word and associated metadata.
 */
public class WordCard {
    private final String word;   // The word displayed on the card
    private final int num;       // A unique identifier for the card within the game
    private final String team;   // Team to which the word is assigned, can be null for neutral words
    private boolean flag = false; // Flag to indicate whether the card has been revealed

    /**
     * Constructs a WordCard with the specified word, card number, and team.
     *
     * @param word The word on the card.
     * @param num  The card's number, used for identifying and referencing the card.
     * @param team The team associated with the word. This can be a specific team or 'BLACK' for black cards,
     *             or it could be null for neutral words.
     */
    public WordCard(String word, int num, String team) {
        this.word = word;
        this.num = num;
        this.team = team;
    }

    /**
     * Marks the card as revealed. Once set, it indicates that the card's word and team are visible to players.
     */
    public void setFlag() { this.flag = true; }

    /**
     * Returns the word on this card.
     * @return The word associated with this card.
     */
    public String getWord() { return word; }

    /**
     * Returns the number of this card.
     * @return The card's unique number.
     */
    public int getNum() { return num; }

    /**
     * Returns the team associated with this card.
     * @return The name of the team, 'BLACK' for black cards, or null for neutral cards.
     */
    public String getTeam() { return team; }

    /**
     * Checks if this card has been revealed.
     * @return true if the card has been revealed, false otherwise.
     */
    public boolean isFlag() { return flag; }
}

