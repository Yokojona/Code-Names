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
    public void makeTurn(Scanner scanner) {
        if (isActiveGame()) {
            Game game = e.getGame();
            GameSpec spec = e.getGameSpec();
            int curr_team_i = game.getCurr_team_i();
            System.out.println("Team name: " + spec.getTeam_names()[curr_team_i]);
            int score = game.getTeam_score()[curr_team_i];
            System.out.println("Team Score: " + score + "/" + spec.getTeam_cards_count()[curr_team_i]);
            System.out.println("Hinter Phase (board revealed!):");
            BoardPrinter.displayBoard(game.getDeck(),
                    spec.getRows(), spec.getColumns(), false);
            System.out.println("Enter Hint:");
            String hint = scanner.nextLine();
            System.out.println("Enter number of relevant words:");
            boolean flag = false;
            int words = 0;
            while (!flag) {
                if (scanner.hasNextInt()) {
                    words = scanner.nextInt();
                    if (words < 1 || words > spec.getCards_count() + spec.getBlack_cards_count()) {
                        System.out.println("invalid number of relevant words");
                    }
                    else {
                        flag = true;
                    }
                } else {
                    System.out.println("Only numbers please");
                    scanner.nextLine();
                }
            }
            System.out.println("Guesser Phase (board hidden!):");
            System.out.println("Hint: " + hint);
            BoardPrinter.displayBoard(game.getDeck(),
                    spec.getRows(), spec.getColumns(), true);
            int[] guesses = new int[words];
            System.out.println("Enter Guesses (card numbers):");
            flag = false;
            int i = 0;
            while (!flag) {
                if (scanner.hasNextInt()) {
                    int guess = scanner.nextInt();
                    if (guess < 1 || guess > spec.getCards_count() + spec.getBlack_cards_count()) {
                        System.out.println("invalid guess number");
                    }
                    else {
                        guesses[i] = guess - 1;
                        i++;
                        if (i == words) flag = true;
                    }
                } else {
                    System.out.println("Only numbers please");
                    scanner.nextLine();
                }
            }
            int[] turnResults = e.gameTurn(guesses);
            game = e.getGame();
            spec = e.getGameSpec();
            System.out.println("Turn Results:");
            for (i = 0; i < turnResults.length; i++) {
                System.out.println("Guess #" + (i + 1) + ": [" + (guesses[i] + 1) + "] " + game.getDeck()[guesses[i]].getWord());
                if (turnResults[i] == 1) {
                    System.out.println("Correct! (+1 point)");
                }
                else if (turnResults[i] == 2) {
                    System.out.println("Opposing team word! (+1 point to " + game.getDeck()[guesses[i]].getTeam() + ")");
                }
                else if (turnResults[i] == 3) {
                    System.out.println("Black word! (game over)");
                }
                else if (turnResults[i] == 4) {
                    System.out.println("Neutral word! (no points)");
                }
                else {
                    break;
                }
            }
            score = game.getTeam_score()[curr_team_i];
            System.out.println("Team Score: " + score + "/" + spec.getTeam_cards_count()[curr_team_i]);
            if (game.isGameOver()) {
                System.out.println("Game Over!");
                int team_i = 0;
                while (team_i < spec.getTeam_names().length) {
                    score = game.getTeam_score()[team_i];
                    System.out.println(spec.getTeam_names()[team_i] + ": " + score + "/" + spec.getTeam_cards_count()[team_i]);                    team_i++;
                }
                e.exitGame();
            }
            else if (game.getWinner() != -1) {
                System.out.println("Winner: " + spec.getTeam_names()[game.getWinner()]);
                int team_i = 0;
                while (team_i < spec.getTeam_names().length) {
                    score = game.getTeam_score()[team_i];
                    System.out.println(spec.getTeam_names()[team_i] + ": " + score + "/" + spec.getTeam_cards_count()[team_i]);                    team_i++;
                }
                e.exitGame();
            }
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
        System.exit(0);
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
