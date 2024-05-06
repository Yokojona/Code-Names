package engine;

import dto.GameSpec;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpecValidator {

    public static int isValid(GameSpec spec) {
        if (spec.getFile_path() == null)
            return 1;
        if (!(spec.getFile_path().endsWith(".xml")))
            return 1;
        if (spec.getCards_count() > spec.getGame_words().length)
            return 2;
        if (spec.getBlack_cards_count() > spec.getBlack_words().length)
            return 3;
        if (Arrays.stream(spec.getTeam_cards_count()).sum() > spec.getCards_count())
            return 4;
        if (spec.getRows() * spec.getColumns() != spec.getCards_count() + spec.getBlack_cards_count())
            return 5;
        Set<String> name_set = new HashSet<>();
        for (String name : spec.getTeam_names())
            if (!name_set.add(name))
                return 6;
        return 0;
    }
}
