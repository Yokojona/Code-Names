import ui.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UI ui = new UIImp();
        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            printMenu(ui);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                if (option == 1) {
                    scanner.nextLine();
                    System.out.println("Enter file path:");
                    String path = scanner.nextLine();
                    // C:\Users\yokoj\IdeaProjects\Code_Names\classic.xml
                    ui.sendFilePathToEngine(path);
                } else if (option == 2) {
                    ui.displayGameSpec();
                } else if (option == 3) {
                    ui.startNewGame();
                } else if (option == 4) {
                    ui.makeTurn(scanner);
                } else if (option == 5) {
                    ui.displayGame(false);
                } else if (option == 6) {
                    ui.exitGame();
                    break;
                } else
                    System.out.println("Invalid option");
            } else {
                System.out.println("Only numbers please");
                scanner.nextLine();
            }
        }
    }

    public static void printMenu(UI ui) {
        System.out.println("(1) Load XML");
        System.out.println("(2) Game Spec");
        System.out.println("(3) New Game");
        if (ui.isActiveGame()) {
            System.out.println("(4) Make Turn " + "(" + ui.getCurrentTeam() + ")");
        } else {
            System.out.println("(4) Make Turn");
        }
        System.out.println("(5) Game Status");
        System.out.println("(6) Exit");
    }
}