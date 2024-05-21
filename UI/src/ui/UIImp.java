package ui;

import dto.*;
import engine.*;
import engine.game.Game;
import ui.input.TurnInputGetter;
import ui.printer.*;

import java.util.Scanner;

/**
 * Implementation of the UI interface managing user interactions and controlling the game flow.
 * This class facilitates user commands execution such as starting a new game, making turns, and displaying game states.
 */
public class UIImp implements UI {
    private final Engine e;

    /**
     * Constructs the UI implementation with a new game engine instance.
     */
    public UIImp() {
        e = new EngineImp();
    }

    @Override
    public void goToMenu(Scanner scanner) {
        MenuPrinter.print(e);
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();
            switch (option) {
                case 1: sendFilePathToEngine(scanner); break;
                case 2: displayGameSpec(); break;
                case 3: startNewGame(); break;
                case 4: makeTurn(scanner); break;
                case 5: displayGame(false); break;
                case 6: exitGame(); break;
                default: System.out.println("Invalid option, please try again");
            }
        } else {
            System.out.println("Only numbers please, try again");
            scanner.nextLine(); // clear the scanner buffer
        }
    }

    @Override
    public void sendFilePathToEngine(Scanner scanner) {
        scanner.nextLine(); // Consume newline left-over
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

    @Override
    public void startNewGame() {
        if (e.getGameSpec() != null) {
            e.startNewGame();
            System.out.println("New game started");
        } else {
            System.out.println("Game spec not found");
        }
    }

    @Override
    public void makeTurn(Scanner scanner) {
        if (e.getGame() == null) {
            System.out.println("Game isn't active");
            return;
        }
        Game game = e.getGame();
        GameSpec spec = e.getGameSpec();
        int curr_team_i = game.getCurr_team_i();
        System.out.println("Team name: " + spec.getTeam_names()[curr_team_i]);
        int score = game.getTeam_score()[curr_team_i];
        System.out.println("Team score: " + score + "/" + spec.getTeam_cards_count()[curr_team_i]);
        System.out.println("Hinter phase (board revealed!):");
        BoardPrinter.print(game.getDeck(), spec.getRows(), spec.getColumns(), false);
        scanner.nextLine();  // Clear buffer
        System.out.println("Enter hint (or 0 to return):");
        String hint = scanner.nextLine();
        if (hint.equals("0")) { return; } // Exit if user chooses to return
        int numGuesses = TurnInputGetter.numGuesses(scanner, spec.getCards_count() + spec.getBlack_cards_count());
        if (numGuesses == 0) { return; } // Exit if no guesses
        System.out.println("Guesser phase (board hidden!):");
        BoardPrinter.print(game.getDeck(), spec.getRows(), spec.getColumns(), true);
        System.out.println("Hint: " + hint);
        System.out.println("Guesses remaining: " + numGuesses);
        int currGuessNum = 0;
        boolean flag = false;
        while (!flag) {
            int guess = TurnInputGetter.guess(scanner);
            if (guess == 0) {
                flag = true; // End turn if user chooses to end
            } else {
                int result = e.gameTurn(guess - 1); // Process the guess
                if (result >= 1) {
                    BoardPrinter.print(game.getDeck(), spec.getRows(), spec.getColumns(), true);
                    GuessResultPrinter.print(result);
                    currGuessNum++;
                    if (currGuessNum == numGuesses || e.getGame().isGameOver()) {
                        flag = true; // End guessing if all guesses are used or game is over
                    } else {
                        System.out.println("Guesses remaining: " + (numGuesses - currGuessNum));
                    }
                } else {
                    GuessResultPrinter.print(result);
                    scanner.nextLine(); // Clear buffer
                }
            }
        }
        e.nextTeam();
        score = game.getTeam_score()[curr_team_i];
        System.out.println("Team Score: " + score + "/" + spec.getTeam_cards_count()[curr_team_i]);
        if (game.isGameOver()) {
            System.out.println("Game Over!");
            ScoresPrinter.print(spec.getTeam_names(), spec.getTeam_cards_count(),
                    game.getTeam_score(), game.getTeam_turn_count(), game.getWinner());
            e.exitGame();
        }
    }

    @Override
    public void displayGame(boolean hidden) {
        if (e.getGame() != null) {
            Game game = e.getGame();
            GameSpec spec = e.getGameSpec();
            BoardPrinter.print(game.getDeck(), spec.getRows(), spec.getColumns(), hidden);
            ScoresPrinter.print(spec.getTeam_names(), spec.getTeam_cards_count(),
                    game.getTeam_score(), game.getTeam_turn_count(), game.getWinner());
        } else {
            System.out.println("Game not found");
        }
    }

    @Override
    public void exitGame() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
