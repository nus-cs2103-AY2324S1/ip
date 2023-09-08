package duke;

import java.util.ArrayList;

// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84

/**
 * Handles user interface-related operations for the Duke application.
 * Provides methods for displaying messages and error messages to the user.
 */
public class Ui {
    private static final String separators = "____________________________________________________________";
    String text1 = " Hello! I'm Novo\n" + " What can I do for you?\n" + separators + "\n";
    String text2 = " Bye. Hope to see you again soon!";

    /**
     * Displays the welcome text to the user.
     */
    public String displayWelcomeText() {
        return separators + "\n" + text1;
    }

    /**
     * Displays the goodbye text to the user.
     */
    public String displayGoodbyeText() {
        return separators + "\n" + text2 + "\n" + separators;
    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to display.
     */
    public String showErrorMessage(String message) {
        return separators + "\n" + message + "\n" + separators;
    }

    /**
     * Displays a message when a task is successfully deleted.
     *
     * @param deletedTask          The task that was deleted.
     * @param remainingNumberTasks The number of tasks remaining in the list.
     */
    public String displayDeleteTask(Task deletedTask, int remainingNumberTasks) {
        return separators + "\n" + "Noted. I've removed this task:" + "\n" + deletedTask.toString() + "\n"
            + "Now you have " + remainingNumberTasks + " tasks in the list." + separators;
    }

    /**
     * Displays a message when a new task is successfully added.
     *
     * @param newTask   The task that was added.
     * @param newNumber The new total number of tasks in the list.
     */
    public String displayAddedTask(Task newTask, int newNumber) {
        return separators + "\n" + "Got it. I've added this task:" + "\n" + newTask.toString() + "\n"
            + "Now you have " + newNumber + " tasks in the list." + separators;
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public String displayTaskList(TaskList taskList) {
        StringBuilder message = new StringBuilder(separators + "\n" + "Here are the tasks in your list:"
            + "\n");
        for (int i = 0; i < taskList.numTasks(); i++) {
            message.append((i + 1)).append(".").append(taskList.getTask(i).toString()).append("\n");
        }
        message.append(separators);
        return message.toString();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param markedTask The task that was marked as done.
     */
    public String displayMarked(Task markedTask) {
        return separators + "\n" + "Nice! I've marked this task as done:" + "\n" + markedTask.toString()
            + separators;
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param markedTask The task that was marked as not done.
     */
    public String displayUnmarked(Task markedTask) {
        return separators + "\n" + "OK, I've marked this task as not done yet:" + "\n" + markedTask.toString()
            + separators;
    }

    /**
     * Displays a list of searched tasks.
     * <p>
     * This method takes an ArrayList of Task objects and prints them to the console,
     * numbering each task for easy reference. It also adds separators before and after
     * the list to visually separate it from other output.
     *
     * @param searchedTask An ArrayList of Task objects containing the tasks to be displayed.
     */
    public String displaySearched(ArrayList<Task> searchedTask) {
        StringBuilder message = new StringBuilder(separators + "\n" + "Here are the matching tasks in your list:"
            + "\n");
        for (int i = 0; i < searchedTask.size(); i++) {
            message.append((i + 1)).append(".").append(searchedTask.get(i).toString()).append("\n");
        }
        message.append(separators);
        return message.toString();
    }

    /**
     * Displays an error message for an empty todo description.
     */
    public String printToDoException() {
        return separators + "\n" + "☹ OOPS!!! The description of a todo cannot be empty." + "\n" + separators;
    }

    /**
     * Displays an error message for an unrecognized command.
     */
    public String printNoSuchElementException() {
        return separators + "\n" + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n" + separators;
    }

    /**
     * Displays an error message for an empty event description.
     */
    public String printEventException() {
        return separators + "\n" + "☹ OOPS!!! The description of an event cannot be empty." + "\n" + separators;
    }

    /**
     * Displays an error message for an empty deadline description.
     */
    public String printDeadlineException() {
        return separators + "\n" + "☹ OOPS!!! The description of a deadline cannot be empty." + "\n" + separators;
    }

    /**
     * Displays an error message for an unspecified task to mark.
     */
    public String printMarkException() {
        return separators + "\n" + "☹ OOPS!!! The task to mark must be specified." + "\n" + separators;
    }

    /**
     * Displays an error message for an unspecified task to unmark.
     */
    public String printUnmarkException() {
        return separators + "\n" + "☹ OOPS!!! The task to unmark must be specified." + "\n" + separators;
    }

    /**
     * Displays an error message for a deadline task in the wrong format.
     */
    public String printSearchException() {
        return separators + "\n" + "☹ OOPS!!! The task to search must be specified." + "\n" + separators;
    }

    public String printDeadlineFormatException() {
        return separators + "\n" + "☹ OOPS!!! Enter in the format: deadline (task) /by dd/MM/yyyy HHmm"
            + "\n" + separators;
    }

    /**
     * Displays an error message for a event task in the wrong format.
     */
    public String printEventFormatException() {
        return separators + "\n" + "☹ OOPS!!! Enter in the format: \n event (task) /from yyyy/MM/dd HHmm /to yyyy/MM/dd HHmm"
            + "\n" + separators;
    }
}
