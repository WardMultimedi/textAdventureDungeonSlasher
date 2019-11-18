package be.multimedi.textAdventureDungeonSlasher;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConsoleTool {
    private static Scanner keyboard = new Scanner(System.in);

    public static void askPressEnterToContinue() {
        System.out.print("Press enter to continue.");
        keyboard.nextLine();
    }

    public static String askUserInputString(String question, int minimumCharacters) {
        if (minimumCharacters <= 0) {
            System.out.print(question);
            return keyboard.nextLine();
        } else {
            String input = null;
            do {
                System.out.print(question);
                input = keyboard.nextLine();
                if (input.length() < minimumCharacters)
                    System.out.format("Error: Input must be at least %d character%s.\n", minimumCharacters, minimumCharacters > 1 ? "s" : "");
            } while (input.length() < minimumCharacters);
            return input;
        }
    }

    public static int askUserInputInteger(String question) {
        int input = 0;
        try {
            System.out.print(question);
            input = keyboard.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println("Error: input is not a number");
        } finally {
            keyboard.nextLine();
        }
        return input;
    }

    public static int askUserInputInteger(String question, int minimum) {
        int input = 0;
        do {
            input = askUserInputInteger(question);
            if (input < minimum) {
                System.out.println("Error: input must be higher or equal to " + minimum);
            }
        } while (input < minimum);
        return input;
    }

    public static int askUserInputInteger(String question, int minimum, int maximum) {
        int input = 0;
        do {
            input = askUserInputInteger(question);
            if (input < minimum) {
                System.out.println("Error: input must be higher or equal to " + minimum);
            } else if (input > maximum) {
                System.out.println("Error: input must be lower or equal to " + maximum);
            }
        } while (input < minimum || input > maximum);
        return input;
    }

    public static boolean askUserYesNoQuestion(String question, boolean useDefaultValue, boolean defaultValue) {
        do {
            System.out.print(question);
            String answer = keyboard.nextLine();
            answer = answer.toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) return true;
            else if (answer.equals("n") || answer.equals("no")) return false;
            else if (useDefaultValue && answer.isBlank()) return defaultValue;
            System.out.println("Error: input must be y or n.");
        } while (true);
    }
}
