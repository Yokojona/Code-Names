package engine.game;


import java.util.Objects;

public class TeamIndexFinder {
    public static int find(String[] teamNamesList, String teamName) {
        for (int i = 0; i < teamNamesList.length; i++) {
            if (Objects.equals(teamName, teamNamesList[i])) {
                return i;
            }
        }
        return -1;
    }
}
