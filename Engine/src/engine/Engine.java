package engine;

import dto.*;

public interface Engine {
    public int readSpecXML(String file_path);
    public GameSpec getGameSpec();
    public void startNewGame();
    public void gameTurn();
    public Game getGame();
    public void exitSystem();
}
