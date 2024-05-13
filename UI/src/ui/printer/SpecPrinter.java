package ui.printer;

import dto.GameSpec;

public class SpecPrinter {
    public static void print(GameSpec spec) {
        if (spec != null) {
            System.out.println("Game Spec:");
            System.out.println("file path: " + spec.getFile_path());
            System.out.println("card count: " + spec.getCards_count());
            System.out.println("black card count: " + spec.getBlack_cards_count());
            System.out.println("dimensions: " + spec.getRows() + "x" + spec.getColumns());
            for (int i = 0; i < spec.getTeam_names().length; i++)
                System.out.println(spec.getTeam_names()[i] + ": " + spec.getTeam_cards_count()[i] + " cards");
        } else {
            System.out.println("Game Spec not found");
        }
    }
}