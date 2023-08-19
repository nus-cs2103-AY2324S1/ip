package rat.print;
public class RatPrinter {

    private static String welcomeMsg = "                  Hello! I'm Rat!\n"
            + "              What can I do for you?";
    private static String exitMsg = "           Bye. Hope to see you again soon!";

    public RatPrinter() { };

    public static void printWelcome() {
        printWithLines(welcomeMsg);
    }

    public static void printExit() {
        printWithLines(exitMsg);
    }

    public static void printWithLines(String msg) {
        System.out.println("============================================================");
        System.out.println(msg);
        System.out.println("============================================================");
    }




}
