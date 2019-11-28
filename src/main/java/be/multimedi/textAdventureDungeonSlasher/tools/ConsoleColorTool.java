package be.multimedi.textAdventureDungeonSlasher.tools;

public class ConsoleColorTool {
   public static void resetPrintgColor(){
      System.out.print(ConsoleColors.RESET.getColorStr());
   }

   public static void setPrintColor(ConsoleColors color){
      System.out.print(color.getColorStr());
   }

   public static void printInColorAndReset(String text, ConsoleColors color){
      StringBuilder sb = new StringBuilder(color.getColorStr());
      sb.append(text).append(ConsoleColors.RESET.getColorStr());
      System.out.println(sb);
   }
}
