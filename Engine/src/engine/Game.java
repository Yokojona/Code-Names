package engine;

import dto.GameSpec;
import dto.WordCard;

public class Game {
    private final WordCard[] deck;
    private final int[] team_score;
    private int curr_team_i = 0;
    private int winner = -1;
    private boolean gameOver = false;

    public Game(GameSpec spec) {
        deck = WordCardFactory.initializeDeck(spec);
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
    public int getWinner() {
        return winner;
    }
    public void setWinner(int winner) { this.winner = winner; }

    public boolean isGameOver() {
        return gameOver;
    }
    public void setGameOver() { this.gameOver = true; }

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
