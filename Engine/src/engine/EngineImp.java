package engine;

public class EngineImp implements Engine {
    private Game game;
    private GameSpec spec;

    public EngineImp() {
    }

    @Override
    public boolean readSpecXML(String file_path) {
        // change this to getting the data from the file
        String[] game_words = new String[]{"apple", "apple", "banana", "banana", "cucumber",
                "cucumber", "drink", "drink", "escargot", "escargot",
                "fish", "fish", "grape", "grape", "heel",
                "heel", "ion", "ion", "jack", "jack",
                "koala", "koala", "lasso", "lasso", "mango", "mango",
                "nectar", "nectar", "oath", "oath", "pineapple",
                "pineapple", "quality", "quality", "resort", "resort",
                "sky", "sky", "trip", "trip", "umbrella",
                "umbrella", "vacation", "vacation", "weekend", "weekend",
                "x-men", "x-men", "yogurt", "yogurt", "zebra", "zebra"};
        String[] black_words = new String[]{"apple", "banana", "cucumber", "drink", "escargot",
                "fish", "grape", "heel", "ion", "jack",
                "koala", "lasso", "mango", "nectar", "oath",
                "pineapple", "quality", "resort", "sky", "trip",
                "umbrella", "vacation", "weekend", "x-men", "yogurt", "zebra",
                "apple", "banana", "cucumber", "drink", "escargot",
                "fish", "grape", "heel", "ion", "jack",
                "koala", "lasso", "mango", "nectar", "oath",
                "pineapple", "quality", "resort", "sky", "trip",
                "umbrella", "vacation", "weekend", "x-men", "yogurt", "zebra"};
        int cards_count = 24;
        int black_cards_count = 1;
        int rows = 5;
        int columns = 5;
        String[] team_names = new String[]{"T1", "T2"};
        int[] team_cards_count = new int[]{9,8};

        spec = new GameSpec(file_path, game_words, black_words,
                cards_count, black_cards_count, rows, columns,
                team_names, team_cards_count);
        return spec.isValid();
    }

    @Override
    public GameSpec getGameSpec() {return spec;}

    @Override
    public void startNewGame() {
        game = new Game(spec);
    }

    @Override
    public void gameTurn() {

    }

    @Override
    public void gameStatus() {

    }

    @Override
    public void exitSystem() {
        System.exit(0);
    }

    public Game getGame() {return game;}
}
