package be.multimedi.textAdventureDungeonSlasher.tools;

public enum ConsoleColors {
   // URL: https://en.wikipedia.org/wiki/ANSI_escape_code#3/4_bit
   FG_RED("31"), FG_GREEN("32"), FG_BLUE("34"), FG_YELLOW("33"),
   FG_BLACK("30"), FG_WHITE("37"),
   BG_RED("41"), BG_GREEN("42"), BG_BLUE("44"), BG_YELLOW("43"),
   BG_BLACK("40"), BG_WHITE("47"),
   RESET("0");


   private String colorStr;

   public String getColorStr() {
      return colorStr;
   }

   private ConsoleColors(String colorStr){
      StringBuilder sb = new StringBuilder("\033[");
      sb.append(colorStr);
      sb.append("m");
      this.colorStr = sb.toString();
   }
}
