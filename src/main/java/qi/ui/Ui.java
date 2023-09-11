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
    public String showWelcome() {
        return "Hello! I'm Qi"
            + "What can I do for you?";
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
    public String showTaskAdded(Task task, TaskList list) {
        return "Got it. I've added this task:\n"
            + " " + task + '\n'
            + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Shows the task removed from the list on the screen.
     *
     * @param taskId Id of the task removed.
     * @param list Current TaskList.
     */
    public String showTaskDeleted(int taskId, TaskList list) {
        return "Noted. I've removed this task:\n"
            + "  " + list.deleteTask(taskId) + '\n'
            + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Shows the task marked as done on the screen.
     *
     * @param taskId Id of the task marked as done.
     * @param list Current TaskList.
     */
    public String showTaskMarked(int taskId, TaskList list) {
        list.mark(taskId, true);
        return "Nice! I've marked this task as done:\n"
            + "  " + list.showTask(taskId);
    }

    /**
     * Shows the task unmarked on the screen.
     *
     * @param taskId Id of the task unmarked.
     * @param list Current TaskList.
     */
    public String showTaskUnmarked(int taskId, TaskList list) {
        list.mark(taskId, false);
        return "OK, I've marked this task as not done yet:\n"
            + "  " + list.showTask(taskId);
    }

    /**
     * Shows the content of the list on the screen.
     *
     * @param list Current TaskList.
     */
    public String showList(TaskList list) {
        return "Here are the tasks in your list:\n"
            + list;
    }

    public String showMatching(String matchingTasks) {
        return "Here are the matching tasks in your list:\n"
            + matchingTasks;
    }


    /**
     * Prints a message that the input is invalid.
     */
    public String showInvalid() {
        return "OOPS!!! I'm sorry, but I don't know what that means :<";
    }

    /**
     * Prints goodbye message on the screen.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints message that the file cannot be loaded.
     */
    public String showLoadingError() {
        return "Cannot load file!";
    }

    /**
     * Shows errors while executing task.
     *
     * @param e Content of the error message.
     */
    public String showError(String e) {
        return "" + e;
    }
}
