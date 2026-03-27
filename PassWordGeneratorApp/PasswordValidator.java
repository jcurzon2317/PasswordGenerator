public class PasswordValidator {

    public boolean matchesConfig(String password, PasswordConfig config) {
        if (password == null || config == null) {
            return false;
        }

        if (config.isUseWordCombo()) {
            return !password.isEmpty();
        }

        if (password.length() != config.getLength()) {
            return false;
        }

        if (config.isUseLetters() && !containsLetter(password)) {
            return false;
        }

        if (config.isUseNumbers() && !containsNumber(password)) {
            return false;
        }

        if (config.isUseSymbols() && !containsSymbol(password)) {
            return false;
        }

        return true;
    }

    private boolean containsLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNumber(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsSymbol(String password) {
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }
}