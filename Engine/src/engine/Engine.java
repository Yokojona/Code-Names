package engine;

public interface Engine {
    public boolean readSpecXML(String file_path);
    public GameSpec getGameSpec();
    public void startNewGame();
    public void gameTurn();
    public void gameStatus();
    public void exitSystem();
}
