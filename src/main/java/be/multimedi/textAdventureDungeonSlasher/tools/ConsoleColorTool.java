package be.multimedi.textAdventureDungeonSlasher.tools;

public class ConsoleColorTool {
   public static void resetPrintgColor(){
      System.out.print(ConsoleColors.RESET.getColorStr());
   }

   public static void setPrintColor(ConsoleColors color){
      System.out.print(color.getColorStr());
   }

   public static String getStringInColor(String text, ConsoleColors color){
      return new StringBuilder(color.getColorStr()).append(text).toString();
   }
   public static String getStringInColor(String text, ConsoleColors startColor,ConsoleColors endColor){
      return new StringBuilder(startColor.getColorStr()).append(text)
              .append(endColor.getColorStr()).toString();
   }

   public static String getStringInColorAndReset(String text, ConsoleColors color){
      return getStringInColor(text, color, ConsoleColors.RESET);
   }

   public static void printInColorAndReset(String text, ConsoleColors color){
      System.out.println(getStringInColorAndReset(text, color));
   }
}
