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
    private Scanner sc;

    /**
     * Constructs the User Interface.
     *
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints and returns the welcome message.
     *
     */
    public String showWelcome() {

        String introduction = " Hello! I'm FootyCouch\n"
                + " What can I do for you?\n";
        showLine();
        System.out.println(introduction);
        showLine();
        return introduction;
    }

    /**
     * Prints the dash line.
     *
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints and returns the error message.
     *
     */
    public String showError(String message) {
        System.out.println(" ☹ OOPS!!! " + message);
        return " OOPS!!! " + message;
    }

    /**
     * Prints and returns the successful add message.
     *
     * @param task the specific task that will be added.
     * @param tasks the list of tasks.
     */
    public String showAdd(Task task, TaskList tasks) {
        String message = " Got it. I've added this task:\n" + "   " + task + "\n"
                        + " Now you have " + tasks.length() + " tasks in the list.";
        System.out.println(message);
        return message;
    }

    /**
     * Prints and returns the successful delete message.
     *
     * @param task the specific task that will be deleted.
     * @param tasks the list of tasks.
     */
    public String showDelete(Task task, TaskList tasks) {
        String message = "Noted. I've removed this task:\n" + "   " + task + "\n"
                        + "Now you have " + tasks.length() + " tasks in the list.";
        System.out.println(message);
        return message;
    }

    /**
     * Prints and returns the exit message.
     *
     */
    public String showExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Prints and returns the successful mark message.
     *
     * @param task the specific task that will be marked.
     */
    public String showMark(Task task) {
        String message = " Nice! I've marked this task as done:\n" + "   " + task;
        System.out.println(message);
        return message;
    }

    /**
     * Prints and returns the successful unmark message.
     *
     * @param task the specific task that will be unmarked.
     */
    public String showUnmark(Task task) {
        String message = " OK, I've marked this task as not done yet:" + "   " + task;
        System.out.println(message);
        return message;
    }

    /**
     * Prints and returns the successful find message.
     *
     * @param tasks the list of filtered tasks that will be printed.
     */
    public String showFind(TaskList tasks) {
        String message = "Here are the matching tasks in your list:\n" + tasks;
        System.out.println(message);
        return message;
    }

    /**
     * Prints and returns the list message.
     *
     * @param tasks the list of all tasks.
     */
    public String showList(TaskList tasks) {
        System.out.println(tasks);
        return tasks.toString();
    }

    /**
     * Read the next Input.
     *
     */
    public String readCommand() {
        return sc.nextLine();
    }
}