import ui.*;

public class Main {
    public static void main(String[] args) {
        UI ui = new UIImp();
        ui.sendFilePathToEngine("FILE_NAME");
        ui.displayGameSpec();
    }
}