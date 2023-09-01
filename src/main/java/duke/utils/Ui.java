package duke.utils;

public class Ui {

    /**
     * Prints out the greeting message when the program is started.
     *
     * @param name
     */
    public static void printGreeting(String name) {
        System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        Ui.printLine();
        System.out.println("You have the following duke.tasks:");
    }

    /**
     * Prints out a line divider.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
