package ui.printer;

public class ScoresPrinter {
    static public void print(String[] teamNames, int[] cardCount, int[] teamScores, int[] teamTurnCount, int winner) {
        for (int i = 0 ; i < teamNames.length; i++) {
            int score = teamScores[i];
            if (winner == i) {
                System.out.println(teamNames[i] + "(" + teamTurnCount[i] + " turns): " + score
                        + "/" + cardCount[i] + " - Winner!");
            }
            else {
                System.out.println(teamNames[i] + "(" + teamTurnCount[i] + " turns): " + score
                        + "/" + cardCount[i]);
            }
        }
    }
}
