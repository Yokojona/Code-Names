import java.util.*;

public class Board {
    private final int rows;
    private final int columns;
    private final int neutralCount;
    private final int Xcount;
    private List<WordCard> cards;
    public Board(String[] words, int rows, int columns, int neutralCount, int Xcount) {
        this.rows = rows; this.columns = columns; this.neutralCount = neutralCount; this.Xcount = Xcount;
        List<String> list = Arrays.asList(words);
        Collections.shuffle(list);
        cards = new ArrayList<>();
        for (int i = 0; i < rows * columns - neutralCount - Xcount ; i++) {
            if (i % 2 == 0)
                cards.add(new WordCard(wordsList.get(i), i + 1, 'a'));
            else
                cards.add(new WordCard(wordsList.get(i), i + 1, 'b'));
        }
        for (int i = 0; i < neutralCount; i++)
            cards.add(new WordCard(wordsList.get(i),  rows * columns - neutralCount - Xcount + i, 'n'));
        for (int i = 0; i < Xcount; i++)
            cards.add(new WordCard(wordsList.get(i),rows * columns - Xcount + i, 'X');

    }
    public int getColumns() {return columns;}
    public int getRows() {return rows;}
    public int getNeutralCount() {return neutralCount;}
    public int getXcount() {return Xcount;}
}
