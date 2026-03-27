import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class WordComboPasswordGenerator implements PasswordGenerator {

    private final SecureRandom random = new SecureRandom();
    private List<String> words;

    public WordComboPasswordGenerator() {
        this.words = loadWords();
    }

    @Override
    public String generate(PasswordConfig config) {
        List<String> selectedWords = pickRandomWords(config.getNumberOfWords());

        if (config.isCapitalizeWords()) {
            selectedWords = capitalizeWords(selectedWords);
        }

        String base = joinWords(selectedWords, config.getSeparator());

        if (config.isAppendNumber()) {
            base = appendNumbers(base);
        }

        return base;
    }

    private List<String> loadWords() {
        try {
            List<String> allWords = Files.readAllLines(Paths.get("words.txt"));
            List<String> filteredWords = new ArrayList<>();

            for (String word : allWords) {
                word = word.trim().toLowerCase();

                if (isUsableWord(word)) {
                    filteredWords.add(word);
                }
            }

            return filteredWords;

        } catch (IOException e) {
            throw new RuntimeException("Failed to load words file.", e);
        }
    }

    private boolean containsVowel(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        String lower = word.toLowerCase();

        return lower.contains("a") ||
               lower.contains("e") ||
               lower.contains("i") ||
               lower.contains("o") ||
               lower.contains("u") ||
               lower.contains("y");
    }

    private boolean isAlphabetic(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isUsableWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        if (!containsVowel(word)) {
            return false;
        }

        if (!isAlphabetic(word)) {
            return false;
        }

        int length = word.length();
        return length >= 3 && length <= 8;
    }

    private List<String> pickRandomWords(int count) {
        if (words == null || words.isEmpty()) {
            throw new IllegalStateException("Word list is empty. Check file loading.");
        }

        if (count > words.size()) {
            throw new IllegalArgumentException(
                "Not enough unique words available to pick " + count + " words."
            );
        }

        List<String> availableWords = new ArrayList<>(words);
        List<String> selected = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int index = random.nextInt(availableWords.size());
            selected.add(availableWords.remove(index));
        }

        return selected;
    }

    private List<String> capitalizeWords(List<String> words) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.add(Character.toUpperCase(word.charAt(0)) + word.substring(1));
            } else {
                result.add(word);
            }
        }

        return result;
    }

    private String joinWords(List<String> words, String separator) {
        return String.join(separator == null ? "" : separator, words);
    }

    private String appendNumbers(String base) {
        int number = random.nextInt(1000);
        return base + number;
    }
}