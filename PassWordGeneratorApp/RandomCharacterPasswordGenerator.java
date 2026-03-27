import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.security.SecureRandom;

public class RandomCharacterPasswordGenerator implements PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!#$%^&*-_+?";

    private final SecureRandom random = new SecureRandom();

    @Override
    public String generate(PasswordConfig config) {
        String pool = buildCharacterPool(config);

        if (pool.isEmpty()) {
            throw new IllegalArgumentException("Character pool is empty.");
        }

        List<Character> passwordChars = new ArrayList<>();

        // Step 1: Ensure at least one of each required type
        if (config.isUseLetters()) {
            passwordChars.add(getRandomChar(
                    (config.isLowercaseAllowed() ? LOWERCASE : "") +
                    (config.isUppercaseAllowed() ? UPPERCASE : "")
            ));
        }

        if (config.isUseNumbers()) {
            passwordChars.add(getRandomChar(NUMBERS));
        }

        if (config.isUseSymbols()) {
            passwordChars.add(getRandomChar(SYMBOLS));
        }

        // Step 2: Fill remaining length
        while (passwordChars.size() < config.getLength()) {
            passwordChars.add(getRandomChar(pool));
        }

        // Step 3: Shuffle so required characters aren't predictable
        Collections.shuffle(passwordChars);

        // Step 4: Convert to string
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

    private String buildCharacterPool(PasswordConfig config) {
        StringBuilder pool = new StringBuilder();

        if (config.isUseLetters()) {
            if (config.isLowercaseAllowed()) {
                pool.append(LOWERCASE);
            }
            if (config.isUppercaseAllowed()) {
                pool.append(UPPERCASE);
            }
        }

        if (config.isUseNumbers()) {
            pool.append(NUMBERS);
        }

        if (config.isUseSymbols()) {
            pool.append(SYMBOLS);
        }

        return pool.toString();
    }

    private char getRandomChar(String source) {
        return source.charAt(random.nextInt(source.length()));
    }
}