package engine;

import dto.GameSpec;
import engine.game.Game;

public interface Engine {
    public int readSpecXML(String file_path);
    public GameSpec getGameSpec();
    public void startNewGame();
    public int[] gameTurn(int[] guesses);
    public Game getGame();
    public void exitGame();
}
