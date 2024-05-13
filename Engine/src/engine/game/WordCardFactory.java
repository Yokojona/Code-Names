package engine.game;

import dto.GameSpec;
import dto.WordCard;

import java.util.*;

public class WordCardFactory {
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

        for (int i = 0; i < teamNames.length; i++) {
            for (int j = 0; j < teamCardsCounts[i]; j++) {
                String word = getNextUniqueWord(allGameWords, usedWords);
                if (word == null) {
                    return null;
                }
                deck[cardI] = new WordCard(word, shuffledNums[cardI], teamNames[i]);
                cardI++;
            }
        }
        for (int i = 0; i < spec.getBlack_cards_count(); i++) {
            String blackWord = getNextUniqueWord(allBlackWords, usedWords);
            if (blackWord == null) {
                return null;
            }
            deck[cardI] = new WordCard(blackWord, shuffledNums[cardI], "BLACK");
            cardI++;
        }
        while (cardI < totalCards) {
            String word = getNextUniqueWord(allGameWords, usedWords);
            if (word == null) {
                return null;
            }
            deck[cardI] = new WordCard(word, shuffledNums[cardI], null);
            cardI++;
        }
        Arrays.sort(deck, Comparator.comparingInt(WordCard::getNum));
        return deck;
    }

    private static String getNextUniqueWord(List<String> words, Set<String> usedWords) {
        for (String word : words) {
            if (!usedWords.contains(word)) {
                usedWords.add(word);
                return word;
            }
        }
        return null;
    }
    private static Integer[] generateAndShuffleNums(int count) {
        Integer[] nums = new Integer[count];
        for (int i = 0; i < count; i++) {
            nums[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(nums));
        return nums;
    }
}
