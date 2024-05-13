package ui;

import dto.*;
import engine.*;
import engine.game.Game;
import ui.printer.BoardPrinter;
import ui.printer.FileLoadMessagePrinter;
import ui.printer.MenuPrinter;
import ui.printer.SpecPrinter;

import java.util.*;

public class UIImp implements UI {
    Engine e;
    public UIImp() {
        e = new EngineImp();
    }

    @Override
    public void goToMenu(Scanner scanner) {
        MenuPrinter.print(e);
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();
            if (option == 1) {
                sendFilePathToEngine(scanner);
            } else if (option == 2) {
                displayGameSpec();
            } else if (option == 3) {
                startNewGame();
            } else if (option == 4) {
                makeTurn(scanner);
            } else if (option == 5) {
                displayGame(false);
            } else if (option == 6) {
                exitGame();
            } else
                System.out.println("Invalid option");
        } else {
            System.out.println("Only numbers please");
            scanner.nextLine();
        }
    }

    @Override
    public void sendFilePathToEngine(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Enter file path:");
        String filePath = scanner.nextLine();
        int valid = e.readSpecXML(filePath);
        FileLoadMessagePrinter.print(valid);
    }

    @Override
    public void displayGameSpec() {
        GameSpec spec = e.getGameSpec();
        SpecPrinter.print(spec);
    }

    public void startNewGame() {
        if (e.getGameSpec() != null) {
            e.startNewGame();
            System.out.println("New game started");
        }
        else {
            System.out.println("Game spec not found");
        }
    }

    @Override
    public void makeTurn(Scanner scanner) {
        if (e.getGame() != null) {
            Game game = e.getGame();
            GameSpec spec = e.getGameSpec();
            int curr_team_i = game.getCurr_team_i();
            System.out.println("Team name: " + spec.getTeam_names()[curr_team_i]);
            int score = game.getTeam_score()[curr_team_i];
            System.out.println("Team score: " + score + "/" + spec.getTeam_cards_count()[curr_team_i]);
            System.out.println("Hinter phase (board revealed!):");
            BoardPrinter.print(game.getDeck(),
                    spec.getRows(), spec.getColumns(), false);
            scanner.nextLine();
            System.out.println("Enter hint (or 0 to return):");
            String hint = scanner.nextLine();
            if (hint.equals("0")) {
                return;
            }
            System.out.println("Enter number of relevant words (or 0 to return):");
            boolean flag = false;
            int words = 0;
            while (!flag) {
                if (scanner.hasNextInt()) {
                    words = scanner.nextInt();
                    if (words == 0) {
                        return;
                    }
                    else if (words < 0 || words > spec.getCards_count() + spec.getBlack_cards_count()) {
                        System.out.println("invalid number of relevant words");
                        scanner.nextLine();
                    }
                    else {
                        flag = true;
                    }
                } else {
                    System.out.println("Only numbers please");
                    scanner.nextLine();
                }
            }
            System.out.println("Guesser phase (board hidden!):");
            System.out.println("Hint: " + hint);
            BoardPrinter.print(game.getDeck(),
                    spec.getRows(), spec.getColumns(), true);
            System.out.println("Guesses remaining: " + words);
            int guessesNum = 0;
            System.out.println("Pick a card number (or 0 to end turn):");
            flag = false;
            while (!flag) {
                if (scanner.hasNextInt()) {
                    int guess = scanner.nextInt();
                    if (guess == 0) {
                        flag = true;
                        e.nextTeam();
                    }
                    else {
                        int result = e.gameTurn(guess - 1);
                        if (result == -1) {
                            System.out.println("Invalid card number, please try again");
                            scanner.nextLine();
                        }
                        else if (result == 0) {
                            System.out.println("Word already guessed, please try again");
                            scanner.nextLine();
                        }
                        else {
                            guessesNum++;
                            if (result == 1) {
                                System.out.println("Correct! (+1 point)");
                            }
                            else if (result == 2) {
                                System.out.println("Opposing team word! (+1 point to "
                                        + game.getDeck()[guess - 1].getTeam() + ")");
                            }
                            else if (result == 3) {
                                System.out.println("Black word!");
                            }
                            else if (result == 4) {
                                System.out.println("Neutral word! (no points)");
                            }
                            else {
                                System.out.println("Invalid guess");
                            }
                            if (guessesNum == words || e.getGame().isGameOver()) {
                                flag = true;
                                e.nextTeam();
                            }
                            else {
                                System.out.println("Guesses remaining: " + (words - guessesNum));
                                System.out.println("Pick a card number (or 0 to end turn):");
                            }
                        }
                    }
                } else {
                    System.out.println("Only numbers please, try again");
                    scanner.nextLine();
                }
            }
            game = e.getGame();
            spec = e.getGameSpec();
            score = game.getTeam_score()[curr_team_i];
            System.out.println("Team Score: " + score + "/" + spec.getTeam_cards_count()[curr_team_i]);
            if (game.isGameOver()) {
                System.out.println("Game Over!");
                for (int i = 0 ; i < spec.getTeam_names().length; i++) {
                    score = game.getTeam_score()[i];
                    if (game.getWinner() == i) {
                        System.out.println(spec.getTeam_names()[i] + ": " + score
                                + "/" + spec.getTeam_cards_count()[i] + " - Winner!");
                    }
                    else {
                        System.out.println(spec.getTeam_names()[i] + ": " + score
                                + "/" + spec.getTeam_cards_count()[i]);
                    }
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
            BoardPrinter.print(e.getGame().getDeck(),
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
}
