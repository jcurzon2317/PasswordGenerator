import java.util.Scanner;

public class PasswordGeneratorApp {
    private final GeneratorFactory generatorFactory;
    private final PasswordValidator validator;
    private final Scanner scanner;

    public PasswordGenerator() {
        this.generatorFactory = new GeneratorFactory();
        this.validator = new PasswordValidator();
        this.scanner = new Scanner(system.in);
    }

    public voic start() {
        System.out.println("=== Password Generator===");

        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    PasswordConfig config = readUserOptions();
                    String password = generatePassword(config);

                    if(password !=null) {
                        printPassword(password);
                    }
                    break;

                case 2:
                    System.out.println("Exiting Password Generator.");
                    running = false;
                    break;

                default:
                    System.out.println("Invaldi choice. Please try again.");
            }

            System.out.println();

        }

        scanner.close();
    }

    public voic showMenu() {
        System.out.println("1. Generate Password");
        System.out.println("2. Exit");
    }

    public PasswordConfig readUserOptions() {
        PasswordConfig config = new PasswordConfig();

        System.out.println("\nSelect password type:");
        System.out.println("1. Letters only");
        System.out.println("2. Letters and number");
        System.out.println("3. Letters, numbers, and symbols");
        System.out.println("4. Random word combo with numbers");

        int typeChoice = readInt("Enter password type: ");

        switch (typechoice) {
            case 1:
                config.setUserLetters(true);
                config.setUserNumbers(false);
                config.setUseSymbols(false);
                config.setUseWordCombo(false);
                config.setLength(readInt("Enter password length: "));
                break;

            case 2:
                config.setUserLetters(true);
                config.setUserNumbers(true);
                config.setUseSymbols(false);
                config.setUseWordCombo(false);
                config.setLength(readInt("Enter password length: "));
                break;

            case 3:
                config.setUserLetters(true);
                config.setUserNumbers(true);
                config.setUseSymbols(true);
                config.setUseWordCombo(false);
                config.setLength(readInt("Enter password length: "));
                break;

            case 4:
                config.setUserLetters(false);
                config.setUserNumbers(true);
                config.setUseSymbols(false);
                config.setUseWordCombo(true);
                config.setLength(readInt("Enter password length: "));

                config.setNumberOfWords(redint("Enter number of words: "));
                config.setSeparator(readLine("Enter separatoor between words (or leave blank); "));
                config.setAppendNumber(readYesNo("Append numbers at the end? (y/n): "));
                config.setCapitalizeWords(readYesNo("Capitalize each word? (y/n): "));
                break;

            default:
                System.out.println("Invalid type selected. Defaulting to letters only.");
                config.setUseLetters(true);
                config.setUseNumbers(false);
                config.setUseSymbols(false);
                config.setUseWordCombo(false);
                config.setLength(12);
        }

        if (!config.isValid()) {
            System.out.println("Warning: Config may not be valid.");
        }

        return config;
    }
public String generatePassword(PasswordConfig config) {
        try {
            PasswordGenerator generator = generatorFactory.getGenerator(config);
            String password = generator.generate(config);

            if (!validator.matchesConfig(password, config)) {
                System.out.println("Generated password did not pass validation.");
                return null;
            }

            return password;
        } catch (Exception e) {
            System.out.println("Error generating password: " + e.getMessage());
            return null;
        }
    }

    public void printPassword(String password) {
        System.out.println("\nGenerated Password:");
        System.out.println(password);
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline
                return value;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // discard invalid input
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Please enter y or n.");
            }
        }
    }

    public static void main(String[] args) {
        PasswordGeneratorApp app = new PasswordGeneratorApp();
        app.start();
    }
}