package be.multimedi.textAdventureDungeonSlasher.tools;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A Utility class to get input from the user using the console
 */
public final class ConsoleInputTool {
   /** Constructor */
   private ConsoleInputTool() {
   }

   /** keyboard/Scanner */
   static Scanner keyboard = new Scanner(System.in);

   /**
    * Requests the user to press the return key to continue.
    */
   public static void askPressEnterToContinue() {
      System.out.print("Press enter to continue.");
      keyboard.nextLine();
   }

   /**
    * Ask the user for a boolean(may repeat untill input is correct).
    *
    * @param question        The question to ask(print to) the user.
    * @param useDefaultValue Use the default value if the user inputs a blank line? yes or no ? true or false.
    * @param defaultValue    The default value to use if the user inputs a blank line.
    * @return the user input: string.
    */
   public static boolean askUserYesNoQuestion(String question, boolean useDefaultValue, boolean defaultValue) {
      do {
         System.out.print(question);
         String answer = keyboard.nextLine();
         answer = answer.toLowerCase();
         if (answer.equals("y") || answer.equals("yes")) return true;
         else if (answer.equals("n") || answer.equals("no")) return false;
         else if (useDefaultValue && answer.isBlank()) return defaultValue;
         System.err.println("Error: input must be y or n.");
      } while (true);
   }

   /**
    * Ask the user for a boolean(repeat untill input is correct).
    *
    * @param question The question to ask(print to) the user.
    * @return the user input: string.
    */
   public static boolean askUserYesNoQuestion(String question) {
      return askUserYesNoQuestion(question, false, false);
   }

   /**
    * Ask the user for a String(repeat untill input is correct).
    *
    * @param question          the question to ask(print to) the user.
    * @param minimumCharacters the minimum length of String to return.
    * @return the user input: string.
    */
   public static String askUserString(String question, int minimumCharacters) {
      if (minimumCharacters <= 0) {
         System.out.print(question);
         return keyboard.nextLine();
      } else {
         String input = null;
         do {
            System.out.print(question);
            input = keyboard.nextLine();
            if (input.length() < minimumCharacters)
               System.err.format("Error: Input must be at least %d character%s.\n", minimumCharacters, minimumCharacters > 1 ? "s" : "");
         } while (input.length() < minimumCharacters);
         return input;
      }
   }

   /**
    * Ask the user for a String(repeat untill input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: string.
    */
   public static String askUserString(String question) {
      return askUserString(question, 0);
   }

   /**
    * Ask the user for a integer(repeat untill input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: integer.
    */
   public static int askUserInteger(String question) {
      int input = 0;
      while(true) {
         try {
            System.out.print(question);
            input = keyboard.nextInt();
            break;
         } catch (InputMismatchException ime) {
            System.err.println("Error: input is not a number");
         } finally {
            keyboard.nextLine();
         }
      }
      return input;
   }

   /**
    * Ask the user for a integer(repeat untill input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @param minimum  the minimum the integer is allowed to be.
    * @return the user input: integer.
    */
   public static int askUserInteger(String question, int minimum) {
      int input = minimum-1;
      do {
         input = askUserInteger(question);
         if (input < minimum) {
            System.err.println("Error: input must be equal or higher than " + minimum);
         }
      } while (input < minimum);
      return input;
   }

   /**
    * Ask the user for a integer(repeat untill input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @param minimum  the minimum the integer is allowed to be.
    * @param maximum  the maximum the integer is allowed to be.
    * @return the user input: integer.
    */
   public static int askUserInteger(String question, int minimum, int maximum) {
      int input = 0;
      do {
         input = askUserInteger(question);
         if (input < minimum) {
            System.err.println("Error: input must be equal or higher than " + minimum);
         } else if (input > maximum) {
            System.err.println("Error: input must be equal or lower than " + maximum);
         }
      } while (input < minimum || input > maximum);
      return input;
   }

   /**
    * Ask the user for a LocalDate(repeat untill input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: LocalDate.
    */
   public static LocalDate askUserDate(String question) {
      LocalDate ld = null;
      do {
         System.out.print(question);
         String date = keyboard.nextLine();
         try {
            ld = LocalDate.parse(date);
         } catch (DateTimeParseException dtpe) {
            System.err.println("Error: " + dtpe.getMessage());
            System.err.println("Expected format: YYYY-MM-DD");
         }
      } while (ld == null);
      return ld;
   }

   /**
    * Ask the user for a LocalDate(repeat untill input is correct).
    *
    * @param question    the question to ask(print to) the user.
    * @param maximumDate the maximum date that is allowed
    * @return the user input: LocalDate.
    */
   public static LocalDate askUserDateBefore(String question, LocalDate maximumDate) {
      LocalDate ld = askUserDate(question);
      while (ld.isAfter(maximumDate) || ld.isEqual(maximumDate)) {
         System.err.println("Error: Date must be before " + maximumDate);
         ld = askUserDate(question);
      }
      return ld;
   }

   /**
    * Ask the user for a LocalDate(repeat untill input is correct).
    *
    * @param question    the question to ask(print to) the user.
    * @param minimumDate the minimum date that is allowed
    * @return the user input: LocalDate.
    */
   public static LocalDate askUserDateAfter(String question, LocalDate minimumDate) {
      LocalDate ld = askUserDate(question);
      while (ld.isBefore(minimumDate) || ld.isEqual(minimumDate)) {
         System.err.println("Error: Date must be before " + minimumDate);
         ld = askUserDate(question);
      }
      return ld;
   }
}
