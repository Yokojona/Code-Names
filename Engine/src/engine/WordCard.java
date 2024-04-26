package engine;

public class WordCard {
    private final String word;
    private final int num;
    private final char team;
    private boolean check = false;
    public WordCard() {this.word = "";this.num = 0;this.team = '\0';}
    public WordCard(String word, int num, char team ) {this.word = word;this.num = num;this.team = team;}
    public String getWord() {return word;}
    public int getNum() {return num;}
    public char getTeam() {return team;}
    public boolean isCheck() {return check;}
    public void setCheck(boolean check) {this.check = check;}
    public void setCheck() {check = true;}
}
