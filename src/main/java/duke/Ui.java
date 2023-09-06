package duke;
import java.util.Scanner;

/**
 * Represents the user interface for interacting with the application.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes a new user interface with a scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a line separator to separate messages.
     */
    public static void showLine() {
        System.out.println("-------------------------------");
    }

    /**
     * Closes the scanner used for user input.
     */
    public void exit() {
        this.scanner.close();
    }

    /**
     * Reads and returns user input as a string.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void welcomeMessage() {
        showLine();
        System.out.println("Hello! I'm Gideon");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a message when a file is successfully created.
     */
    public void createFileMessage() {
        System.out.println("File created: Gideon");
    }

    /**
     * Displays a message when a saved file is opened.
     */
    public void openFileMessage() {
        System.out.println("Opening saved file.");
    }

    /**
     * Displays a message when there are no tasks in the task list.
     */
    public void emptyTaskListMessage() {
        System.out.println("There are no tasks.");
    }

    /**
     * Displays a list of tasks.
     *
     * @param taskList The list of tasks to display.
     */
    public void showTasks(TaskList taskList) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.toString());
        showLine();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void markedMessage(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        showLine();
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void unmarkedMessage(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        showLine();
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task       The task that was added.
     * @param numOfTasks The number of tasks in the list after adding.
     */
    public void addTaskMessage(Task task, int numOfTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message when a task is deleted from the task list.
     *
     * @param task       The task that was deleted.
     * @param numOfTasks The number of tasks in the list after deletion.
     */
    public void deleteTaskMessage(Task task, int numOfTasks) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        showLine();
    }

    /**
     * Displays a message for an invalid user input or command.
     */
    public void invalidTaskMessage() {
        showLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        showLine();
    }
}
