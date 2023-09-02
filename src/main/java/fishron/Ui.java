package fishron;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Ui` class handles user interaction and displays messages to the console.
 * It provides methods for showing welcome and goodbye messages, reading user commands,
 * displaying task-related information, and showing error messages.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates a new `Ui` object and initializes the scanner for user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user when the application starts.
     */
    public void showWelcomeMessage() {
        System.out.println(
                "___________________________________________________________\n"
                        + "Hello! I'm fishron.Fishron\n"
                        + "What can I do for you?");
    }

    /**
     * Displays a goodbye message to the user when the application exits.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a horizontal line as a separator in the console.
     */
    public void showLine() {
        System.out.println("___________________________________________________________");
    }

    /**
     * Reads a user command from the console and returns it as a string.
     *
     * @return The user-entered command.
     */
    public String readCommand() {
        String userCommand = sc.nextLine();
        return userCommand;
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMsg The error message to be displayed.
     */
    public void showErrorMessage(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Displays a message to indicate that a task has been successfully added.
     *
     * @param taskList The task list containing the added task.
     */
    public void showTaskAdded(TaskList taskList) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getList().get(taskList.getSize() - 1).toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The task list to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> list = taskList.getList();
        int start = 1;
        for (Task listItems : list) {
            System.out.println(start + ". " + listItems.toString());
            start++;
        }
    }

    /**
     * Displays a message to indicate that a task has been successfully deleted.
     *
     * @param tasklist The task list containing the deleted task.
     * @param deleted  The deleted task.
     */
    public void showTaskDeleted(TaskList tasklist, Task deleted) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deleted);
        System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
    }

    /**
     * Displays a message to indicate that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showDoneTask(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays a message to indicate that a task has been marked as undone.
     *
     * @param task The task that has been marked as undone.
     */
    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("I've marked this task as undone:");
        System.out.println("  " + task.toString());
    }
}
