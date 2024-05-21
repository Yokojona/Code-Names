package engine;

import dto.*;
import engine.game.Game;
import engine.game.TeamIndexFinder;
import engine.jaxb.JaxbParser;
import engine.jaxb.SpecValidator;

import java.util.Objects;

/**
 * Implementation of the Engine interface, managing the game's operational logic including
 * game state management, XML configuration reading, and gameplay functionality.
 */
public class EngineImp implements Engine {
    private Game game;       // Instance of Game handling the current game state.
    private GameSpec spec;   // GameSpec object holding the configuration of the current game.

    public EngineImp() {
        // Constructor for EngineImp, no initializations needed here.
    }

    @Override
    public int readSpecXML(String file_path) {
        GameSpec newSpec = JaxbParser.ParseFile(file_path);
        if (newSpec == null) {
            return 1;  // Indicates error in parsing the XML file.
        }
        int errorCode = SpecValidator.isValid(newSpec);
        if (errorCode == 0) {
            spec = newSpec;
            exitGame();  // Resets any existing game with the new spec loaded.
        }
        return errorCode;
    }

    @Override
    public GameSpec getGameSpec() {
        return spec;
    }

    @Override
    public void startNewGame() {
        game = new Game(spec);
    }

    @Override
    public int gameTurn(int guess) {
        if (guess < 0 || guess >= game.getDeck().length) {
            return -1;  // Guess is out of valid card index range.
        }
        WordCard word = game.getDeck()[guess];
        if (word.isFlag()) {
            return 0;  // Card has already been revealed.
        }
        int res = 0;
        int curr_team_i = game.getCurr_team_i();
        if (word.getTeam() != null && !Objects.equals(word.getTeam(), "BLACK")) {
            // Find out which team the card belongs to.
            int word_team_i = TeamIndexFinder.find(spec.getTeam_names(), word.getTeam());
            game.getTeam_score()[word_team_i]++;
            // 1 if the guess was correct, 2 if the card belongs to a different team.
            res = (curr_team_i == word_team_i) ? 1 : 2;
            if (game.getTeam_score()[word_team_i] == spec.getTeam_cards_count()[word_team_i]) {
                game.setWinner(word_team_i);
                game.setGameOver();
            }
        } else if (Objects.equals(word.getTeam(), "BLACK")) {
            game.setGameOver();
            res = 3;  // Black card guessed, game over.
        } else {
            res = 4;  // Neutral card guessed.
        }
        word.setFlag();
        return res;
    }

    @Override
    public void nextTeam() {
        game.increment_curr_team_i();
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void exitGame() {
        game = null;
    }
}

