/**
 * This class helps Alyssa interact with the user.
 */
public class Ui {
    private static final String line = "____________________________________________________________";
    private static final String logo = " ___  __    __   __  ____   ____    ___\n"
            + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
            + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
            + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
            + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
            + "|   | |_____| |_|    |____| |____| |   |\n";
    protected void greet() {
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    protected void goodbye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
    protected void invalidTaskResponse() {
        System.out.println(line);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
    }
    protected void printWithLines(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }
}
