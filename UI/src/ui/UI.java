package ui;

import java.util.Scanner;

public interface UI {
    public void goToMenu(Scanner scanner);
    public void sendFilePathToEngine(Scanner scanner);
    public void displayGameSpec();
    public void startNewGame();
    public void makeTurn(Scanner scanner);
    public void displayGame(boolean hidden);
    public void exitGame();
}
