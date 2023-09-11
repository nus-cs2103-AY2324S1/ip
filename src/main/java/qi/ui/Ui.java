package qi.ui;

import java.util.Scanner;

import qi.task.Task;
import qi.tasklist.TaskList;

/**
 * Represents the interactions of the chatbot with users.
 */
public class Ui {

    private Scanner sc;

    /**
     * Initializes the chatbot
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcoming message on the screen.
     */
    public void showWelcome() {
        showLine();
        System.out.println("     Hello! I'm Qi");
        System.out.println("     What can I do for you?");
        showLine();
        System.out.println();
    }

    /**
     * Reads string input from users.
     *
     * @return String input read.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Shows the task added to the list on the screen.
     *
     * @param task Task added to the list.
     * @param list Current TaskList.
     */
    public void showTaskAdded(Task task, TaskList list) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Shows the task removed from the list on the screen.
     *
     * @param taskId Id of the task removed.
     * @param list Current TaskList.
     */
    public void showTaskDeleted(int taskId, TaskList list) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + list.deleteTask(taskId));
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Shows the task marked as done on the screen.
     *
     * @param taskId Id of the task marked as done.
     * @param list Current TaskList.
     */
    public void showTaskMarked(int taskId, TaskList list) {
        list.mark(taskId, true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + list.showTask(taskId));
    }

    /**
     * Shows the task unmarked on the screen.
     *
     * @param taskId Id of the task unmarked.
     * @param list Current TaskList.
     */
    public void showTaskUnmarked(int taskId, TaskList list) {
        list.mark(taskId, false);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + list.showTask(taskId));
    }

    /**
     * Shows the content of the list on the screen.
     *
     * @param list Current TaskList.
     */
    public void showList(TaskList list) {
        System.out.println("     Here are the tasks in your list:");
        System.out.print(list);
    }

    public void showMatching(String matchingTasks) {
        System.out.println("     Here are the matching tasks in your list:");
        System.out.print(matchingTasks);
    }

    /**
     * Prints long separating line on the screen.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message that the input is invalid.
     */
    public void showInvalid() {
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints goodbye message on the screen.
     */
    public void showGoodbye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * Prints message that the file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("Cannot load file!");
    }

    /**
     * Shows errors while executing task.
     *
     * @param e Content of the error message.
     */
    public void showError(String e) {
        System.out.println("     " + e);
    }
}
