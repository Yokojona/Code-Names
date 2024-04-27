package engine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameSpec {
    private final String file_path;
    private final String[] game_words;
    private final String[] black_words;
    private final int cards_count;
    private final int black_cards_count;
    private final int rows;
    private final int columns;
    private final String[] team_names;
    private final int[] team_cards_count;

    public GameSpec(String file_path, String[] game_words, String[] black_words,
                    int cards_count, int black_cards_count, int rows, int columns,
                    String[] team_names, int[] team_cards_count) {
        this.file_path = file_path;
        this.game_words = game_words;
        this.black_words = black_words;
        this.cards_count = cards_count;
        this.black_cards_count = black_cards_count;
        this.rows = rows;
        this.columns = columns;
        this.team_names = team_names;
        this.team_cards_count = team_cards_count;
    }

    public int isValid() {
        if (file_path == null)
            return 1;
        if (!file_path.endsWith(".xml"))
            return 1;
        if (cards_count > game_words.length)
            return 2;
        if (black_cards_count > black_words.length)
            return 3;
        if (Arrays.stream(team_cards_count).sum() > cards_count)
            return 4;
        if (rows * columns != cards_count + black_cards_count)
            return 5;
        Set<String> name_set = new HashSet<>();
        for (String name : team_names)
            if (!name_set.add(name))
                return 6;
        return 0;
    }

    public String getFile_path() {return file_path;}
    public String[] getGame_words() {return game_words;}
    public String[] getBlack_words() {return black_words;}
    public int getCards_count() {return cards_count;}
    public int getBlack_cards_count() {return black_cards_count;}
    public int getRows() {return rows;}
    public int getColumns() {return columns;}
    public String[] getTeam_names() {return team_names;}
    public int[] getTeam_cards_count() {return team_cards_count;}
}
