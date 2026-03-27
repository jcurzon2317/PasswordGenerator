public class PasswordConfig {

    // Core settings
    private int length;

    private boolean useLetters;
    private boolean useNumbers;
    private boolean useSymbols;
    private boolean useWordCombo;

    // Letter options
    private boolean uppercaseAllowed = true;
    private boolean lowercaseAllowed = true;

    // Word combo options
    private int numberOfWords;
    private String separator = "";
    private boolean appendNumber;
    private boolean capitalizeWords;

    // --- Constructors ---
    public PasswordConfig() {
    }

    // --- Validation ---
    public boolean isValid() {
        if (useWordCombo) {
            return numberOfWords > 0;
        }

        if (length <= 0) {
            return false;
        }

        if (!useLetters && !useNumbers && !useSymbols) {
            return false;
        }

        return true;
    }

    // --- Helper ---
    public String getComplexityMode() {
        if (useWordCombo) return "WORD_COMBO";
        if (useLetters && !useNumbers && !useSymbols) return "LETTERS_ONLY";
        if (useLetters && useNumbers && !useSymbols) return "LETTERS_NUMBERS";
        if (useLetters && useNumbers && useSymbols) return "FULL";
        return "CUSTOM";
    }

    // --- Getters & Setters ---

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isUseLetters() {
        return useLetters;
    }

    public void setUseLetters(boolean useLetters) {
        this.useLetters = useLetters;
    }

    public boolean isUseNumbers() {
        return useNumbers;
    }

    public void setUseNumbers(boolean useNumbers) {
        this.useNumbers = useNumbers;
    }

    public boolean isUseSymbols() {
        return useSymbols;
    }

    public void setUseSymbols(boolean useSymbols) {
        this.useSymbols = useSymbols;
    }

    public boolean isUseWordCombo() {
        return useWordCombo;
    }

    public void setUseWordCombo(boolean useWordCombo) {
        this.useWordCombo = useWordCombo;
    }

    public boolean isUppercaseAllowed() {
        return uppercaseAllowed;
    }

    public void setUppercaseAllowed(boolean uppercaseAllowed) {
        this.uppercaseAllowed = uppercaseAllowed;
    }

    public boolean isLowercaseAllowed() {
        return lowercaseAllowed;
    }

    public void setLowercaseAllowed(boolean lowercaseAllowed) {
        this.lowercaseAllowed = lowercaseAllowed;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public boolean isAppendNumber() {
        return appendNumber;
    }

    public void setAppendNumber(boolean appendNumber) {
        this.appendNumber = appendNumber;
    }

    public boolean isCapitalizeWords() {
        return capitalizeWords;
    }

    public void setCapitalizeWords(boolean capitalizeWords) {
        this.capitalizeWords = capitalizeWords;
    }
}