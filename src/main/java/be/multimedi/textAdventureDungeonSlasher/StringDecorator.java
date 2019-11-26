package be.multimedi.textAdventureDungeonSlasher;

/**
 * A decorating tool for Strings
 */
public class StringDecorator {

   /**
    * Decorate String as an alert and print it at the same time
    * @param message message to decorate, if null then this will make a line
    * @param width length of the messages in the alert
    */
   public static void printAlert(String message, int width) {
      if (width == 0) width = 40;
      if (message == null) {
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < width; i++)
            sb.append('-');
         System.out.printf("-!!-%s-!!-\n", sb.toString());
      } else {
         System.out.printf(" ## %-" + width + "s ##\n", message);
      }
   }

   /**
    * Decorate String as an alert and print it at the same time
    * @param message message to decorate, if null then this will make a line
    */
   public static void printAlert(String message) {
      printAlert(message, 0);
   }
}
