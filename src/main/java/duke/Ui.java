
package duke;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import java.util.Scanner;

/**
 * The `Ui` class is responsible for managing user interaction and displaying messages in the Duke application.
 * It handles both user input and program output in the command-line interface (CLI).
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Displays a welcome message when the Duke application is started.
     */
    public void hello() {
        System.out.println("__________________________________________________________");
        System.out.println("Hello, I'm Sori! What can I do for you?");
        System.out.println("__________________________________________________________");
    }

    /**
     * Closes the scanner used for user input and displays a goodbye message.
     *
     * @return A goodbye message.
     */
    public String bye() {
        sc.close();
        return "Bye. Hope to see you again soon!\n__________________________________________________________";
    }

    /**
     * Reads and returns user input as a string.
     *
     * @return The user's input as a string.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Returns a list of tasks from the provided `TaskList`.
     *
     * @param list The `TaskList` containing the tasks to display.
     * @return A formatted string displaying the list of tasks.
     */
    public String displayList(TaskList list) {
        String res = "";
        res += "Here are the tasks in your list:\n";
        res += list.toString();
        res += "\n__________________________________________________________";
        return res;
    }

    /**
     * Returns a message indicating that a task has been successfully added to the list.
     *
     * @param task The task that was added.
     * @param len  The total number of tasks in the list after adding.
     * @return A formatted string confirming the addition of the task.
     */
    public String showAdded(Task task, int len) {
        String res = "";
        res += "Got it. I've added this task:\n";
        res += task.toString();
        res += "\nNow I have " + len + " tasks in the list";
        return res;
    }

    /**
     * Returns a message indicating that a task has been successfully removed from the list.
     *
     * @param task The task that was removed.
     * @param len  The total number of tasks in the list after removal.
     * @return A formatted string confirming the removal of the task.
     */
    public String showDel(Task task, int len) {
        String res = "";
        res += "Noted. I've removed this task:\n";
        res += task.toString();
        res += "\nNow you have " + len +  " tasks in the list";
        return res;
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return A formatted string confirming the task as done.
     */
    public String showDone(Task task) {
        String res = "";
        res += "Nice! I've marked this task as done:\n" + task.toString();
        return res;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return A formatted string confirming the task as not done.
     */
    public String showUnDone(Task task) {
        String res = "";
        res += "OK, I've marked this task as not done yet:\n";
        res += task.toString();
        return res;
    }

    /**
     * Displays an exception message when an error occurs in the Duke application.
     *
     * @param e The exception that occurred.
     * @return The error message associated with the exception.
     */
    public String showException(Exception e) {
        return e.getMessage();
    }
}

