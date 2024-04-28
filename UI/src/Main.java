import ui.*;

public class Main {
    public static void main(String[] args) {
        UI ui = new UIImp();
        ui.sendFilePathToEngine(args[0]);
        ui.displayGameSpec();
        ui.exitGame();
    }
}