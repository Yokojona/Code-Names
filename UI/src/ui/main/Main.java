package ui.main;

import ui.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UI ui = new UIImp();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ui.goToMenu(scanner);
        }
    }

}