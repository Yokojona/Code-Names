package engine.game;

import dto.GameSpec;
import dto.WordCard;

public class Game {
    private final WordCard[] deck;
    private final int[] team_score;
    private final int[] team_turn_count;
    private int curr_team_i = 0;
    private int winner = -1;
    private boolean gameOver = false;

    public Game(GameSpec spec) {
        deck = WordCardFactory.initializeDeck(spec);
        int teamNum = spec.getTeam_names().length;
        team_score = new int[teamNum];
        team_turn_count = new int[teamNum];
        winner = -1;
    }

    public WordCard[] getDeck() { return deck; }

    public int[] getTeam_score() {
        return team_score;
    }

    public int[] getTeam_turn_count() {
        return team_turn_count;
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
        team_turn_count[curr_team_i++]++;
        if (curr_team_i >= team_score.length) {
            curr_team_i = 0;
        }
    }
}
