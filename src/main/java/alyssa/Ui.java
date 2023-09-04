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
    protected String getGreeting() {
        String output = "Hello! I'm Alyssa!\n";
        output += "What can I do for you?\n";
        return output;
    }
    /**
     * Returns a goodbye message.
     */
    protected String goodbye() {
        return "Bye. Hope to see you again soon! Subsequent messages entered will cause the program to close.";
    }
    /**
     * Returns an error message.
     */
    protected String invalidTaskResponse() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
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
