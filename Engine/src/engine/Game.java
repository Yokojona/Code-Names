package engine;

public class Game {
    private final WordCard[] deck;
    private final int[] team_score;
    private int curr_team_i = 0;
    private int winner = -1;
    private boolean gameOver = false;

    public Game(GameSpec spec) {
        deck = GameSpec2Deck.initializeDeck(spec);
        team_score = new int[spec.getTeam_names().length];
        winner = -1;
    }

    public WordCard[] getDeck() { return deck; }

    public int[] getTeam_score() {
        return team_score;
    }

    public void revealCard(int cardI) {
        deck[cardI].setFlag();
        if (deck[cardI].getTeam().equals("BLACK")) {
            gameOver = true;
        }
    }

    public void givePoint(int teamI) {
        team_score[teamI] += 1;
    }

    public boolean winningCondition(int teamI, int team_card_count) {
        if (team_score[teamI] == team_card_count) {
            winner = teamI;
            return true;
        }
        return false;
    }

    public int getWinner() {
        return winner;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCurr_team_i() {
        return curr_team_i;
    }

    public void increment_curr_team_i() {
        curr_team_i++;
        if (curr_team_i >= team_score.length) {
            curr_team_i = 0;
        }
    }
}
