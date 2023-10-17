package duke.utils;

/**
 * A utility class that provides functionality for
 * printing messages to the standard output.
 */
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
     * Prints out the help message.
     */
    public static void printHelp() {
        String help = "Here are the available commands:\n" +
                "list: List out your current tasks\n" +
                "bye: Exit the application\n" +
                "todo <task name>: Add a new task that is not time-specific\n" +
                "deadline <task name> /by <date>: Add a new task with a deadline\n" +
                "event <task name> /from <date> /to <date>: Add a new event with a time period\n" +
                "mark <task number>: Mark the specified task as completed\n" +
                "unmark <task number>: Mark the specified task as not completed\n" +
                "delete <task number>: Delete the specified task\n" +
                "find <keyword>: Finds all tasks containing the keyword\n";

        System.out.println(help);
    }

    /**
     * Prints out a line divider.
     */
    public static void printLine() {
        System.out.print("____________________________________________________________\n");
    }
}
