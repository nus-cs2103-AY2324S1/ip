package remy;

import java.util.Scanner;

import remy.task.Task;

/**
 * Reads user input and outputs Remy's response in the correct format.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "I'm Remy, and it is NOT nice to see you.\n"
            + "Tell me what you want and scram.";

    private static final String EXIT_MESSAGE = "Hope to never see you again!\n";

    public final Scanner scanner;

    /**
     * Constructs new Ui object that is able to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns Remy's welcome message which is shown when the application starts.
     *
     * @return
     */
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns Remy's exit message which is shown when user types Exit Command.
     *
     * @return
     */
    public static String getExitMessage() {
        return EXIT_MESSAGE;
    }

    public static String formatError(String errorMessage) {
        return "❗❗❗" + errorMessage + "❗❗❗";
    }

    /**
     * Prints out the "Successfully added task" message with the following details:
     * 1. String representation of Task added
     * 2. Total tasks in the taskList.
     * @param task Task that has been added.
     * @param num Number of tasks in the TaskList (after adding).
     * @return
     */
    public static String getAddedTaskMessage(Task task, int num) {
        String taskWord = num == 1 ? "task" : "tasks";
        String content = "Added, now scram.\n" + task.toString() + "\n"
                + "Now you have " + num + " " + taskWord + " in the list.";
        return content;
    }

}
