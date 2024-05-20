package engine.game;

import dto.GameSpec;
import dto.WordCard;

import java.util.*;

/**
 * Factory class for creating and initializing a deck of WordCard objects.
 * This class ensures that each card is uniquely assigned based on game specifications,
 * including shuffling of card numbers and ensuring no repeat of words across the deck.
 */
public class WordCardFactory {

    /**
     * Initializes a deck of WordCard objects based on the provided GameSpec.
     * The deck is created with no repeated words and shuffled card indices for random order.
     *
     * @param spec The game specifications containing word lists and counts.
     * @return An array of initialized WordCard objects or null if not enough unique words are available.
     */
    static public WordCard[] initializeDeck(GameSpec spec) {
        int totalCards = spec.getCards_count() + spec.getBlack_cards_count();
        WordCard[] deck = new WordCard[totalCards];
        List<String> allGameWords = new ArrayList<>(Arrays.asList(spec.getGame_words()));
        Collections.shuffle(allGameWords);
        List<String> allBlackWords = new ArrayList<>(Arrays.asList(spec.getBlack_words()));
        Collections.shuffle(allBlackWords);
        Set<String> usedWords = new HashSet<>();
        String[] teamNames = spec.getTeam_names();
        int[] teamCardsCounts = spec.getTeam_cards_count();
        Integer[] shuffledNums = generateAndShuffleNums(totalCards);
        int cardI = 0;

        // Assign words to team cards
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = 0; j < teamCardsCounts[i]; j++) {
                String word = getNextUniqueWord(allGameWords, usedWords);
                if (word == null) {
                    return null;  // Return null if there are not enough unique words
                }
                deck[cardI] = new WordCard(word, shuffledNums[cardI], teamNames[i]);
                cardI++;
            }
        }

        // Assign black words to cards
        for (int i = 0; i < spec.getBlack_cards_count(); i++) {
            String blackWord = getNextUniqueWord(allBlackWords, usedWords);
            if (blackWord == null) {
                return null;
            }
            deck[cardI] = new WordCard(blackWord, shuffledNums[cardI], "BLACK");
            cardI++;
        }

        // Assign remaining cards as neutral
        while (cardI < totalCards) {
            String word = getNextUniqueWord(allGameWords, usedWords);
            if (word == null) {
                return null;
            }
            deck[cardI] = new WordCard(word, shuffledNums[cardI], null);
            cardI++;
        }

        // Sort deck by the Num field
        Arrays.sort(deck, Comparator.comparingInt(WordCard::getNum));
        return deck;
    }

    /**
     * Retrieves the next unique word from the list that hasn't been used yet.
     *
     * @param words The list of words to select from.
     * @param usedWords A set of words that have already been used.
     * @return A unique word or null if all words are used.
     */
    private static String getNextUniqueWord(List<String> words, Set<String> usedWords) {
        for (String word : words) {
            if (!usedWords.contains(word)) {
                usedWords.add(word);
                return word;
            }
        }
        return null;
    }

    /**
     * Generates and shuffles an array of integers for unique card numbering.
     *
     * @param count The total number of cards.
     * @return A shuffled array of integers from 1 to count.
     */
    private static Integer[] generateAndShuffleNums(int count) {
        Integer[] nums = new Integer[count];
        for (int i = 0; i < count; i++) {
            nums[i] = i + 1; // Initialize numbers sequentially
        }
        Collections.shuffle(Arrays.asList(nums)); // Shuffle the numbers
        return nums;
    }
}

