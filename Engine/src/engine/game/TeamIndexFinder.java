package engine.game;

import java.util.Objects;

/**
 * Provides utility methods for working with team data within the game.
 */
public class TeamIndexFinder {

    /**
     * Searches for a given team name within an array of team names and returns the index of the team.
     * This method uses {@link Objects#equals(Object, Object)} to safely compare names, handling nulls.
     *
     * @param teamNamesList The array of team names to search through.
     * @param teamName The name of the team to find.
     * @return The index of the team name in the array if found, otherwise -1.
     */
    public static int find(String[] teamNamesList, String teamName) {
        for (int i = 0; i < teamNamesList.length; i++) {
            if (Objects.equals(teamName, teamNamesList[i])) {
                return i; // Return the index of the team if the names match
            }
        }
        return -1; // Return -1 if no match is found
    }
}

