package engine.jaxb;

import dto.GameSpec;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Validates the integrity and consistency of a GameSpec object.
 * This class ensures that the GameSpec provided meets all necessary conditions
 * for a valid game setup, such as file validation, count checks, and uniqueness of team names.
 */
public class SpecValidator {

    /**
     * Validates a GameSpec object against various rules to ensure it can be used to configure a game correctly.
     *
     * @param spec The GameSpec object to validate.
     * @return an integer code representing the status of the validation:
     *         0 - Valid specification.
     *         1 - Invalid file path or file is not an XML.
     *         2 - Number of game cards is greater than the number of game words provided.
     *         3 - Number of black cards is greater than the number of black words provided.
     *         4 - Sum of team cards exceeds the total number of cards.
     *         5 - Product of rows and columns does not equal the total number of cards (including black cards).
     *         6 - Duplicate team names found.
     */
    public static int isValid(GameSpec spec) {
        if (spec.getFile_path() == null || !spec.getFile_path().endsWith(".xml"))
            return 1;  // Check file path is not null and it must end with .xml
        if (spec.getCards_count() > spec.getGame_words().length)
            return 2;  // Ensure there are enough game words for the cards
        if (spec.getBlack_cards_count() > spec.getBlack_words().length)
            return 3;  // Ensure there are enough black words for the black cards
        if (Arrays.stream(spec.getTeam_cards_count()).sum() > spec.getCards_count())
            return 4;  // Check if the total cards distributed to teams do not exceed available cards
        if (spec.getRows() * spec.getColumns() != spec.getCards_count() + spec.getBlack_cards_count())
            return 5;  // Ensure the board size matches the total number of cards
        Set<String> name_set = new HashSet<>();
        for (String name : spec.getTeam_names()) {
            if (!name_set.add(name))
                return 6;  // Check for duplicate team names
        }
        return 0;  // Return 0 if all checks pass indicating a valid spec
    }
}
