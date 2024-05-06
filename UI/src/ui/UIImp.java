package ui;

import dto.*;
import engine.*;

import java.util.Scanner;

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
            System.out.println("Game Spec not found");
        }
    }

    @Override
    public void makeTurn() {
        Scanner in = new Scanner(System.in);
        if (isActiveGame()) {
            Game game = e.getGame();
            int i = e.getGame().getCurr_team_i();
            System.out.println("Team name: " + e.getGameSpec().getTeam_names()[i]);
            int score = e.getGame().getTeam_score()[i];
            System.out.println("Team Score: " + score + "/" + e.getGameSpec().getTeam_cards_count()[i]);
            System.out.println("Hinter Phase (board revealed!):");
            BoardPrinter.displayBoard(e.getGame().getDeck(),
                    e.getGameSpec().getRows(), e.getGameSpec().getColumns(), false);
            System.out.println("Enter Hint:");
            String hint = in.nextLine();
            System.out.println("Enter number of relevant words:");
            boolean flag = false;
            while (!flag) {
                if (in.hasNextInt()) {
                    int words = in.nextInt();
                    if (words > e.getGameSpec().getCards_count() + e.getGameSpec().getBlack_cards_count()) {
                        System.out.println("too many words");
                    }
                    else {
                        flag = true;
                    }
                } else
                    System.out.println("Only numbers please");
            }
            e.gameTurn();
        }
        else {
            System.out.println("Game isn't active");
        }

    }

    @Override
    public void displayGame(boolean hidden) {
        if (e.getGame() != null) {
            BoardPrinter.displayBoard(e.getGame().getDeck(),
                    e.getGameSpec().getRows(), e.getGameSpec().getColumns(), false);
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

    @Override
    public String getCurrentTeam() {
        int i = e.getGame().getCurr_team_i();
        return e.getGameSpec().getTeam_names()[i];
    }

    @Override
    public boolean isActiveGame() {
        Game g = e.getGame();
        return (g != null);
    }
}
