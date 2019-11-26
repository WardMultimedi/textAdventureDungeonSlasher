package be.multimedi.textAdventureDungeonSlasher;

public class StringDecorator {

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

   public static void printAlert(String message) {
      printAlert(message, 0);
   }
}
