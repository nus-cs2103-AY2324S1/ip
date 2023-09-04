package alyssa;

/**
 * This class helps Alyssa interact with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = " ___  __    __   __  ____   ____    ___\n"
            + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
            + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
            + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
            + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
            + "|   | |_____| |_|    |____| |____| |   |\n";

    /**
     * Sends a greeting message via System.out.println.
     */
    protected void greet() {
        System.out.println(LOGO);
        System.out.println(LINE);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    /**
     * Sends a goodbye message via System.out.println.
     */
    protected void goodbye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
    /**
     * Sends a error message via System.out.println.
     */
    protected void invalidTaskResponse() {
        System.out.println(LINE);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE);
    }
    /**
     * Prints any message, surrounded by lines, via System.out.println.
     */
    protected void printWithLines(String output) {
        System.out.println(LINE);
        System.out.println(output);
        System.out.println(LINE);
    }
}
