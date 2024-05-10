package ui;

import java.util.Scanner;

public interface UI {
    public void sendFilePathToEngine(String filePath);
    public void displayGameSpec();
    public void startNewGame();
    public void makeTurn(Scanner scanner);
    public void displayGame(boolean hidden);
    public void exitGame();

    public String getCurrentTeam();

    boolean isActiveGame();
}
