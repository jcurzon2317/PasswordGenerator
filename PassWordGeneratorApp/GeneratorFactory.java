public class GeneratorFactory {

    public PasswordGenerator getGenerator(PasswordConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("PasswordConfig cannot be null.");
        }

        if (!config.isValid()) {
            throw new IllegalArgumentException("PasswordConfig is not valid.");
        }

        if (config.isUseWordCombo()) {
            return new WordComboPasswordGenerator();
        }

        return new RandomCharacterPasswordGenerator();
    }
}