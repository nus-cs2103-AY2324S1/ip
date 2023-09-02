package duke;

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
    public void displayWelcomeText() {
        System.out.println(separators + "\n" + text1);
    }

    /**
     * Displays the goodbye text to the user.
     */
    public void displayGoodbyeText() {
        System.out.println(separators + "\n" + text2 + "\n" + separators);
    }

    /**
     * Shows an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println(separators + "\n" + message + "\n" + separators);
    }

    /**
     * Displays a message when a task is successfully deleted.
     *
     * @param deletedTask         The task that was deleted.
     * @param remainingNumberTasks The number of tasks remaining in the list.
     */
    public void displayDeleteTask(Task deletedTask, int remainingNumberTasks) {
        System.out.println(separators);
        System.out.println("Noted. I've removed this task:" + "\n" + deletedTask.toString());
        System.out.println("Now you have " + remainingNumberTasks + " tasks in the list.");
        System.out.println(separators);
    }

    /**
     * Displays a message when a new task is successfully added.
     *
     * @param newTask   The task that was added.
     * @param newNumber The new total number of tasks in the list.
     */
    public void displayAddedTask(Task newTask, int newNumber) {
        System.out.println(separators);
        System.out.println("Got it. I've added this task:" + "\n" + newTask.toString());
        System.out.println("Now you have " + newNumber + " tasks in the list.");
        System.out.println(separators);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public void displayTaskList(TaskList taskList) {
        System.out.println(separators);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.numTasks(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i).toString());
        }
        System.out.println(separators);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param markedTask The task that was marked as done.
     */
    public void displayMarked(Task markedTask) {
        System.out.println(separators);
        System.out.println("Nice! I've marked this task as done:" + "\n" + markedTask.toString());
        System.out.println(separators);

    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param markedTask The task that was marked as not done.
     */
    public void displayUnmarked(Task markedTask) {
        System.out.println(separators);
        System.out.println("OK, I've marked this task as not done yet:" + "\n" + markedTask.toString());
        System.out.println(separators);
    }

    /**
     * Displays an error message for an empty todo description.
     */
    public void printToDoException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for an unrecognized command.
     */
    public void printNoSuchElementException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for an empty event description.
     */
    public void printEventException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for an empty deadline description.
     */
    public void printDeadlineException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for an unspecified task to mark.
     */
    public void printMarkException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The task to mark must be specified.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for an unspecified task to unmark.
     */
    public void printUnmarkException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! The task to unmark must be specified.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for a deadline task in the wrong format.
     */
    public void printDeadlineFormatException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! Enter in the format: deadline (task) /by dd/MM/yyyy HHmm");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message for a event task in the wrong format.
     */
    public void printEventFormatException() {
        System.out.println("____________________________________________________________");
        System.out.println("☹ OOPS!!! Enter in the format: \n event (task) /from yyyy/MM/dd HHmm /to yyyy/MM/dd HHmm");
        System.out.println("____________________________________________________________");
    }
}