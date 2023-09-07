package duke.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.data.task.Task;

/**
 * Represents the text UI of the chatbot application.
 */
public class Ui {

    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private final String botName;
    private final Scanner scanner;


    /**
     * Initializes the chatbot with the given name.
     *
     * @param botName
     */
    public Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the given message. If multiple messages are
     * specified, each message is after a new line.
     *
     * @param message The message to be printed.
     */
    public String getMessage(String... message) {
        return String.join("\n", Arrays.asList(message));
    }

    /**
     * Prints the given error message to the user.
     *
     * @param message The error message to be printed.
     */
    public String getErrorMessage(String message) {
        return getMessage("OOPS!!! " + message);
    }

    /**
     * Returns the welcome message.
     */
    public String getWelcomeMessage() {
        return getMessage("Hello! I'm " + botName, "What can I do for you?");
    }

    /**
     * Returns the exit message.
     */
    public String getExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Returns the message indicating there was an error in the loading
     * of the files.
     */
    public String getLoadingErrorMessage() {
        return getMessage("There was an error in loading the existing tasks.");
    }

    /**
     * Returns the list of tasks as a formatted indexed list.
     *
     * @param tasks The tasks to be formatted as an indexed list.
     */
    public String getListMessage(List<? extends Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Returns the filtered list of tasks as a formatted indexed list.
     *
     * @param tasks The filtered list of tasks to be formatted as an indexed list.
     */
    public String getFilteredListMessage(List<? extends Task> tasks) {
        String listMessage = getListMessage(tasks);

        if (tasks.size() == 0) {
            return "There are no matching tasks in your list." + listMessage;
        }
        return "Here are the matching tasks in your list:\n" + listMessage;
    }
}
