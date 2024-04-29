package engine;

public class Game {
    WordCard[] deck;

    public Game(GameSpec spec) {
        deck = GameSpec2Deck.initializeDeck(spec);
    }

    public WordCard[] getDeck() { return deck; }
}
