package ui;

public interface UI {
    public void sendFilePathToEngine(String filePath);
    public void displayGameSpec();
    public void startNewGame();
    public void displayGame();
    public void exitGame();
}