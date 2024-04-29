package engine;

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

    @Override
    public String toString() {
        return "WordCard{" +
                "word='" + word + '\'' +
                ", num=" + num +
                ", team='" + team + '\'' +
                ", flag=" + flag +
                '}';
    }

    public void setFlag() {this.flag = true;}
    public void setFlag(boolean flag) {this.flag = flag;}
    public String getWord() {return word;}
    public int getNum() {return num;}
    public String getTeam() {return team;}
    public boolean isFlag() {return flag;}
}
