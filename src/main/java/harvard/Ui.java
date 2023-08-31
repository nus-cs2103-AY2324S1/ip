package harvard;
import java.util.Scanner;
/**
 * Represents a user interface.
 */

public class Ui {
    /**
     * The scanner.
     */
    Scanner scanner;
    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Displays the welcome message.
     */
    public void displayWelcome() {
        displayLine();
        System.out.println("Hello! I'm Harvard\nWhat can I do for you?");
        displayLine();
    }
    /**
     * Reads the command.
     * @return The command in String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    /**
     * Displays a line.
     */
    public void displayLine() {
        System.out.println("____________________________________________________________");
    }
    /**
     * Displays the bye message.
     */
    public void displayBye() {
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }

    /**
     * Displays adding task message.
     * @param task The task to be added.
     * @param tasks The list of tasks.
     */
    public void showAddTask(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }
    /**
     * Displays the list of tasks.
     * @param tasks The list of tasks.
     */
    public void showList(TaskList tasks) {
        displayLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        displayLine();
    }

    /**
     * Shows the delete message.
     * @param task The task to be deleted.
     * @param tasks The list of tasks.
     */
    public void showDelete(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Noted. I've removed this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }

    /**
     * Shows the done message.
     * @param task The task to be marked as done.
     */
    public void showDone(Task task) {
        displayLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        displayLine();
    }

    /**
     * Shows the undone message.
     * @param task The task to be marked as undone.
     */

    public void showUndone(Task task) {
        displayLine();
        System.out.println("Ok! I've marked this task as not done yet:");
        displayLine();
    }
    /**
     * Displays the error message.
     * @param e The exception.
     */

    public void displayError(DukeException e) {
        System.out.println(e.getMessage());
    }
}
