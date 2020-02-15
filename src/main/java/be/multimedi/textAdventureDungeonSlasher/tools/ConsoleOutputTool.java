package be.multimedi.textAdventureDungeonSlasher.tools;

public final class ConsoleOutputTool {
    /** Constructor */
    private ConsoleOutputTool() {
    }

    /** default title underlining character */
    static final char DEFAULT_TITLE_LINE_CHAR = '━';
    /** default alert width */
    static final int DEFAULT_ALERT_WIDTH = 40;

    /**
     * Decorate String as a title and print it at the same time
     *
     * @param text message to decorate
     * @param type the character underlining the title
     */
    public static void printTitle(String text, char type) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(type);
        }
        System.out.println(text);
        System.out.println(sb.toString());
    }

    /**
     * Decorate String as a title and print it at the same time
     *
     * @param text message to decorate
     */
    public static void printTitle(String text) {
        printTitle(text, DEFAULT_TITLE_LINE_CHAR);
    }

    /**
     * reset output color
     */
    public static void resetPrintColor() {
        System.out.print(ConsoleColors.RESET.getColorStr());
    }

    /**
     * set output color
     */
    public static void setPrintColor(ConsoleColors color) {
        System.out.print(color.getColorStr());
    }

    /**
     * decorate string with output color and reset and prints it out
     * @param text the message to decorate
     * @param color the color in witch te message should be
     */
    public static void printInColorAndReset(String text, ConsoleColors color) {
        StringBuilder sb = new StringBuilder(color.getColorStr());
        sb.append(text).append(ConsoleColors.RESET.getColorStr());
        System.out.print(sb);
    }

    /**
     * decorate string with output color and reset and prints it out + end of line
     * @param text the message to decorate
     * @param color the color in witch te message should be
     */
    public static void printlnInColorAndReset(String text, ConsoleColors color) {
        printInColorAndReset(text, color);
        System.out.println();
    }

    /**
     * Decorate String as an alert and print it at the same time
     *
     * @param text  message to decorate, if null then this will make a line
     * @param width length of the messages in the alert
     */
    public static void printAlert(String text, int width) {
        if (width == 0) width = DEFAULT_ALERT_WIDTH;
        if (text == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < width; i++)
                sb.append('-');
            System.out.printf("-!!-%s-!!-\n", sb.toString());
        } else {
            System.out.printf(" ## %-" + width + "s ##\n", text);
        }
    }

    /**
     * Decorate String as an alert and print it at the same time
     *
     * @param text message to decorate, if null then this will make a line
     */
    public static void printAlert(String text) {
        printAlert(text, 0);
    }

}
