package engine;

import dto.*;
import engine.jaxb.JaxbParser;

import java.util.Objects;

public class EngineImp implements Engine {
    private Game game;
    private GameSpec spec;

    public EngineImp() {
    }

    @Override
    public int readSpecXML(String file_path) {
        GameSpec newSpec = JaxbParser.ParseFile(file_path);
        if (newSpec == null) { return -1; }
        int errorCode = SpecValidator.isValid(newSpec);
        boolean validSpec = errorCode == 0;
        if (validSpec)
            spec = newSpec;
        return errorCode;
    }

    @Override
    public GameSpec getGameSpec() {return spec;}

    @Override
    public void startNewGame() {
        game = new Game(spec);
    }

    @Override
    public int[] gameTurn(int[] guesses) {
        int[] res = new int[guesses.length];
        int i = 0, curr_team_i = game.getCurr_team_i();
        for (int guess : guesses) {
            WordCard word = game.getDeck()[guess];
            word.setFlag();
            if (Objects.equals(word.getTeam(), spec.getTeam_names()[curr_team_i])) {
                game.getTeam_score()[curr_team_i]++;
                res[i] = 1;
                if (game.getTeam_score()[curr_team_i] == spec.getTeam_cards_count()[curr_team_i]) {
                    game.setWinner(curr_team_i);
                    break;
                }
            }
            else if (!Objects.equals(word.getTeam(), "BLACK") && word.getTeam() != null) {
                int opposing_team_i = 0;
                for (int j = 0; j < spec.getTeam_names().length; j++) {
                    if (Objects.equals(word.getTeam(), spec.getTeam_names()[j])) {
                        opposing_team_i = j;
                        break;
                    }
                }
                game.getTeam_score()[opposing_team_i]++;
                res[i] = 2;
                if (game.getTeam_score()[opposing_team_i] == spec.getTeam_cards_count()[opposing_team_i]) {
                    game.setWinner(opposing_team_i);
                    break;
                }
            }
            else if (Objects.equals(word.getTeam(), "BLACK")) {
                game.setGameOver();
                res[i] = 3;
                break;
            }
            else if (word.getTeam() == null) {
                res[i] = 4;
            }
            i++;
        }
        game.increment_curr_team_i();
        return res;
    }

    @Override
    public Game getGame() { return game; }

    @Override
    public void exitGame() { game = null; }
}
