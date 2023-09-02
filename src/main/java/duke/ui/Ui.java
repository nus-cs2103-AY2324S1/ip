package duke.ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

/**
 * User interface for the Duke application.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class Ui {
    Scanner sc;

    /**
     * Constructs the User Interface.
     *
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Print the welcome message.
     *
     */
    public void showWelcome() {
        String Introduction = "____________________________________________________________\n" +
                " Hello! I'm FootyCouch\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(Introduction);
    }

    /**
     * Print the dash line.
     *
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Print the error message.
     *
     */
    public void showError(String message) {
        System.out.println(" ☹ OOPS!!! " + message);
    }

    /**
     * Print the successful add message.
     *
     * @param task the specific task that will be added
     * @param tasks the list of tasks.
     */
    public void printAdd(Task task, TaskList tasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.length() + " tasks in the list.");
    }

    /**
     * Print the successful delete message.
     *
     * @param task the specific task that will be added
     * @param tasks the list of tasks.
     */
    public void printDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + tasks.length() + " tasks in the list.");
    }

    /**
     * Print the exit message.
     *
     */
    public void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Print the successful mark message.
     *
     * @param task the specific task that will be marked.
     */
    public void printMark(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /**
     * Print the successful unmark message.
     *
     * @param task the specific task that will be unmarked.
     */
    public void printUnmark(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * Print the successful find message.
     *
     * @param tasks the list of filtered tasks that will be printed.
     */
    public void printFind(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks);
    }

    /**
     * Print the list message.
     *
     * @param tasks the list of all tasks.
     */
    public void printList(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Read the next Input.
     *
     */
    public String readCommand() {
        return sc.nextLine();
    }
}