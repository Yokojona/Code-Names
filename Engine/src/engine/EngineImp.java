package engine;

import dto.*;
import engine.game.Game;
import engine.game.TeamIndexFinder;
import engine.jaxb.JaxbParser;
import engine.jaxb.SpecValidator;

import java.util.Objects;

public class EngineImp implements Engine {
    private Game game;
    private GameSpec spec;

    public EngineImp() {
    }

    @Override
    public int readSpecXML(String file_path) {
        GameSpec newSpec = JaxbParser.ParseFile(file_path);
        if (newSpec == null) { return 1; }
        int errorCode = SpecValidator.isValid(newSpec);
        boolean validSpec = errorCode == 0;
        if (validSpec) {
            spec = newSpec;
            exitGame();
        }
        return errorCode;
    }

    @Override
    public GameSpec getGameSpec() { return spec; }

    @Override
    public void startNewGame() { game = new Game(spec); }

    @Override
    public int gameTurn(int guess) {
        if (guess < 0 || guess > spec.getCards_count() + spec.getBlack_cards_count())
            return -1;
        WordCard word = game.getDeck()[guess];
        if (word.isFlag())
            return 0;
        int res = 0, curr_team_i = game.getCurr_team_i();
        if (!Objects.equals(word.getTeam(), "BLACK") && word.getTeam() != null) {
            int word_team_i = TeamIndexFinder.find(spec.getTeam_names(), word.getTeam());
            game.getTeam_score()[word_team_i]++;
            res = (curr_team_i == word_team_i) ? 1 : 2;
            if (game.getTeam_score()[word_team_i] == spec.getTeam_cards_count()[word_team_i]) {
                game.setWinner(word_team_i);
                game.setGameOver();
            }
        }
        else if (Objects.equals(word.getTeam(), "BLACK")) {
            game.setGameOver();
            res = 3;
        }
        else if (word.getTeam() == null) {
            res = 4;
        }
        word.setFlag();
        return res;
    }

    @Override
    public void nextTeam() {
        game.increment_curr_team_i();
    }

    @Override
    public Game getGame() { return game; }

    @Override
    public void exitGame() { game = null; }
}
