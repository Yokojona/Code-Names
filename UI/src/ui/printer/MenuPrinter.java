package ui.printer;

import engine.Engine;

public class MenuPrinter {
    public static void print(Engine e) {
        System.out.println("(1) Load XML");
        System.out.println("(2) Game Spec");
        System.out.println("(3) New Game");
        if (e.getGame() != null) {
            System.out.println("(4) Make Turn " + "(" +
                    e.getGameSpec().getTeam_names()[e.getGame().getCurr_team_i()] + ")");
        } else {
            System.out.println("(4) Make Turn");
        }
        System.out.println("(5) Game Status");
        System.out.println("(6) Exit");
    }
}