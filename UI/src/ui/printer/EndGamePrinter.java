package ui.printer;

public class EndGamePrinter {
    static public void print(String[] teamNames, int[] cardCount, int[] teamScores, int winner) {
        System.out.println("Game Over!");
        for (int i = 0 ; i < teamNames.length; i++) {
            int score = teamScores[i];
            if (winner == i) {
                System.out.println(teamNames[i] + ": " + score
                        + "/" + cardCount[i] + " - Winner!");
            }
            else {
                System.out.println(teamNames[i] + ": " + score
                        + "/" + cardCount[i]);
            }
        }
    }
}
