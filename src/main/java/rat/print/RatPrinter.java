package rat.print;
public class RatPrinter {

    public RatPrinter() { }

    public static void printWelcome() {
        String welcomeMsg = "                  Hello! I'm Rat!\n"
                + "              What can I do for you?\n"
                + "       type \"help\" to see a list of commands";
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

}
