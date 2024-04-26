package ui;

import engine.Engine;
import engine.EngineImp;
import engine.GameSpec;

public class UIImp implements UI {
    Engine engine;
    public UIImp() {
        this.engine = new EngineImp();
    }

    @Override
    public void sendFilePathToEngine(String filePath) {
        this.engine.readSpecXML(filePath);
    }

    @Override
    public void displayGameSpec() {
        GameSpec spec = this.engine.getGameSpec();
        System.out.println("Game Spec:\n");
        System.out.println("file path: " + spec.getFile_path() + "\n");
        System.out.println("card count: " + spec.getCards_count() + "\n");
        System.out.println("black card count: " + spec.getBlack_cards_count() + "\n");
        System.out.println("dimensions: " + spec.getRows() + "x" + spec.getColumns() + "\n");
        for (int i = 0; i < spec.getTeam_names().length; i++)
            System.out.println(spec.getTeam_names()[i] + ": " + spec.getTeam_cards_count()[i] + " cards" + "\n");
    }
}
