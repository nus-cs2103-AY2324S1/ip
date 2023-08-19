package rat.print;
public class RatPrinter {

    public static final String ANSI_YELLOW = "\033[0;33m";
    public static final String ANSI_RESET = "\033[0m";

    public RatPrinter() { }

    public static void printWelcome() {
        String welcomeMsg = "                  Hello! I'm Rat!\n"
                + "              What can I do for you?";
        printWithLines(welcomeMsg);
    }

    public static void printExit() {
        String exitMsg = "           Bye. Hope to see you again soon!";
        printWithLines(exitMsg);
    }

    public static void printWithLines(String msg) {
        String output = "============================================================\n"
                + msg + "\n"
                + "============================================================\n";
        System.out.println(output);
    }

    public static String formatWithLines(String msg) {
        return "============================================================\n"
                + msg + "\n"
                + "============================================================\n";
    }

    public static String italicise(String msg) {
        return "\033[3m" + msg;
    }

    public static void printWarning(String warning) {
        System.out.println(ANSI_YELLOW + formatWithLines(warning) + ANSI_RESET);
    }




}
