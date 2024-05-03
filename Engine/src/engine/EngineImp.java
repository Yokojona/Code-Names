package engine;

public class EngineImp implements Engine {
    private Game game;
    private GameSpec spec;

    public EngineImp() {
    }

    @Override
    public int readSpecXML(String file_path) {
        GameSpec newSpec = XML2GameSpec.ParseFile(file_path);
        if (newSpec == null) { return 1; }
        int errorCode = newSpec.isValid();
        boolean validSpec = errorCode == 0;
        if (validSpec)
            spec = newSpec;
        return errorCode;
    }

    @Override
    public GameSpec getGameSpec() {return spec;}

    @Override
    public void startNewGame() {
        game = new Game(spec);
    }

    @Override
    public void gameTurn() {
        game.increment_curr_team_i();
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void exitSystem() {

    }
}
