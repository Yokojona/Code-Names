package dto;

public class WordCard {
    private final String word;
    private final int num;
    private final String team;
    private boolean flag = false;
    public WordCard(String word, int num, String team) {
        this.word = word;
        this.num = num;
        this.team = team;
    }

    public void setFlag() {this.flag = true;}
    public String getWord() {return word;}
    public int getNum() {return num;}
    public String getTeam() {return team;}
    public boolean isFlag() {return flag;}
}
