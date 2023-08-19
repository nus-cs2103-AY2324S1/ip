package rat.print;
public class Printer {

    static String welcomeMsg = "                  Hello! I'm Rat!\n"
            + "              What can I do for you?";
    static String exitMsg = "           Bye. Hope to see you again soon!";

    public Printer() { };

    public static void printWelcome() {
        printWithLines(welcomeMsg);
    }

    public static void printExit() {
        printWithLines(exitMsg);
    }

    public static void printWithLines(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }




}
