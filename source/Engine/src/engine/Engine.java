package engine;

import dto.GameSpec;
import engine.game.Game;

/**
 * Defines the contract for the game engine managing the setup and gameplay logic.
 * This interface outlines methods required for reading game specifications, managing game state,
 * handling player actions, and controlling the game flow.
 */
public interface Engine {

    /**
     * Reads the game specifications from an XML file and initializes the game based on these specifications.
     *
     * @param file_path The path to the XML file containing the game specifications.
     * @return An error code indicating the success or failure of the operation. Typically, '0' indicates success.
     */
    public int readSpecXML(String file_path);

    /**
     * Retrieves the current game specifications.
     *
     * @return The current GameSpec object, which includes details about the game setup.
     */
    public GameSpec getGameSpec();

    /**
     * Initializes a new game based on the current game specifications.
     */
    public void startNewGame();

    /**
     * Processes a player's guess at a specific index during the game.
     *
     * @param guess The index of the card that the player has chosen to guess.
     * @return An integer indicating the result of the guess. This might include error codes or success indicators.
     */
    public int gameTurn(int guess);

    /**
     * Advances the game to the next team's turn.
     */
    public void nextTeam();

    /**
     * Retrieves the current game object managing the state and logic of the ongoing game.
     *
     * @return The current instance of the Game.
     */
    public Game getGame();

    /**
     * Ends the current game session and cleans up the game state.
     */
    public void exitGame();
}
