package ui;

import engine.*;

public class UIImp implements UI {
    Engine e;
    public UIImp() {
        e = new EngineImp();
    }

    @Override
    public void sendFilePathToEngine(String filePath) {
        int valid = e.readSpecXML(filePath);
        if (valid == 0) {
            System.out.println("File loaded successfully");
        }
        else {
            System.out.println("Error:");
            if (valid == 1) {
                System.out.println("File not found (or is not .xml)");
            } else if (valid == 2) {
                System.out.println("More word cards than words");
            } else if (valid == 3) {
                System.out.println("More black word cards than black words");
            } else if (valid == 4) {
                System.out.println("More team cards than word cards");
            } else if (valid == 5) {
                System.out.println("Board dimensions don't fit the number of cards");
            } else if (valid == 6) {
                System.out.println("Team names are not unique");
            } else {
                System.out.println("Unexpected error");
            }
        }
    }

    @Override
    public void displayGameSpec() {
        GameSpec spec = e.getGameSpec();
        if (spec != null) {
            System.out.println("Game Spec:");
            System.out.println("file path: " + spec.getFile_path());
            System.out.println("card count: " + spec.getCards_count());
            System.out.println("black card count: " + spec.getBlack_cards_count());
            System.out.println("dimensions: " + spec.getRows() + "x" + spec.getColumns());
            for (int i = 0; i < spec.getTeam_names().length; i++)
                System.out.println(spec.getTeam_names()[i] + ": " + spec.getTeam_cards_count()[i] + " cards");
        }
        else {
            System.out.println("Game Spec not found");
        }
    }

    public void startNewGame() {
        if (e.getGameSpec() != null) {
            e.startNewGame();
            System.out.println("New game started");
        }
        else {
            System.out.println("No game spec found");
        }
    }

    @Override
    public void displayGame() {
        Game game = e.getGame();
        if (game != null) {
            WordCard[] deck = game.getDeck();
            BoardPrinter.displayBoard(deck, e.getGameSpec().getRows(), e.getGameSpec().getColumns(), true);
        }
        else {
            System.out.println("Game not found");
        }
    }

    @Override
    public void exitGame() {
        System.out.println("Goodbye!");
        e.exitSystem();
    }
}
