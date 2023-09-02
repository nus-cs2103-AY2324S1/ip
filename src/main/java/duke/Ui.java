package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Provides user interface functionalities for the Duke application.
 * This class handles displaying messages, taking user input, and showing various responses.
 */
public class Ui {
    private Scanner scanner;

    private String currentMessage = "";

    /**
     * Constructs a Ui object with a new Scanner to read user input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message along with the Duke logo.
     */
    public void showWelcome() {
        String logo =
                ".______     ______   .___________.\n"
                        + "|   _  \\   /  __  \\  |           |\n"
                        + "|  |_)  | |  |  |  | `---|  |----`\n"
                        + "|   _  <  |  |  |  |     |  |     \n"
                        + "|  |_)  | |  `--'  |     |  |     \n"
                        + "|______/   \\______/      |__|     \n";

        currentMessage += "Hello I'm\n" + logo + "What can I do for you?";
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void showBye() {
        currentMessage = "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message indicating the start of the task list display.
     */
    public void showList() {
        currentMessage += "Here are the tasks in your list: \n";
    }

    /**
     * Displays a generic message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        currentMessage += message;
    }

    /**
     * Displays an error message associated with a DukeException.
     *
     * @param exception The DukeException containing the error message.
     */
    public void showErrMessage(DukeException exception) {
        currentMessage += exception.getMessage();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void markMessage(Task task) {
        currentMessage += "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void unmarkMessage(Task task) {
        currentMessage += "Ok, I've marked this task as not done yet: \n" + task;
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task  The task that was deleted.
     * @param total The total number of tasks after deletion.
     */
    public void deleteMessage(Task task, int total) {
        currentMessage += "Noted. I've removed this task: \n" + task + "\n"
                + "Now you have " + total + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task  The task that was added.
     * @param total The total number of tasks after addition.
     */
    public void addMessage(Task task, int total) {
        currentMessage += "Got it. I've added this task: \n" + task + "\n"
                + "Now you have " + total + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a keyword is being searched.
     */
    public void findMessage() {
        currentMessage += "Here are the matching tasks in your list: \n";
    }

    /**
     * Displays a message indicating that there is no results found.
     */
    public void findNoMessage() {
        currentMessage += "There are no results matching your keyword.";
    }

    public String getCurrentMessage() {
        String temp = currentMessage;
        currentMessage = "";
        return temp;
    }
}
