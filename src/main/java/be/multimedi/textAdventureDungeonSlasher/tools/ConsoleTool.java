package be.multimedi.textAdventureDungeonSlasher.tools;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConsoleTool {
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
         System.out.println("Error: input must be y or n.");
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
               System.err.format("Error: Input must be at least %d character%s.\n", minimumCharacters, minimumCharacters > 1 ? "s" : "");
         } while (input.length() < minimumCharacters);
         return input;
      }
   }

   /**
    * Ask the user for a integer(repeat untill input is correct).
    *
    * @param question the question to ask(print to) the user.
    * @return the user input: integer.
    */
   public static int askUserInputInteger(String question) {
      int input = 0;
      try {
         System.out.print(question);
         input = keyboard.nextInt();
      } catch (InputMismatchException ime) {
         System.err.println("Error: input is not a number");
      } finally {
         keyboard.nextLine();
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
   public static int askUserInputInteger(String question, int minimum) {
      int input = 0;
      do {
         input = askUserInputInteger(question);
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
   public static int askUserInputInteger(String question, int minimum, int maximum) {
      int input = 0;
      do {
         input = askUserInputInteger(question);
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
   public static LocalDate askUserInputDate(String question) {
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
    * @param maximumDate the maximum date the inputDate is allowed to be.
    * @return the user input: LocalDate.
    */
   public static LocalDate askUserInputDateBefore(String question, LocalDate maximumDate) {
      LocalDate ld = askUserInputDate(question);
      while (ld.isAfter(maximumDate) || ld.isEqual(maximumDate)) {
         System.err.println("Error: Date must be before " + maximumDate);
         ld = askUserInputDate(question);
      }
      return ld;
   }

   /**
    * Ask the user for a LocalDate(repeat untill input is correct).
    *
    * @param question    the question to ask(print to) the user.
    * @param minimumDate the minimum date the inputDate is allowed to be.
    * @param maximumDate the maximum date the inputDate is allowed to be.
    * @return the user input: LocalDate.
    */
   public static LocalDate askUserInputDateBetween(String question, LocalDate minimumDate, LocalDate maximumDate) {
      LocalDate ld = askUserInputDate(question);
      while (ld.isBefore(minimumDate) || ld.isEqual(minimumDate) ||
              ld.isAfter(maximumDate) || ld.isEqual(maximumDate)) {
         System.err.println("Error: Date must be between " + minimumDate + " and " + maximumDate);
         ld = askUserInputDate(question);
      }
      return ld;
   }
}
