package ui;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;


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
     * @return The welcome message as a string.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Fishron\n" + "What can I do for you?";
    }

    /**
     * Displays a goodbye message to the user when the application exits.
     * @return The goodbye message as a string.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a horizontal line as a separator in the console.
     * @return The horizontal line as a string.
     */
    public String showLine() {
        return "___________________________________________________________";
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
     * @return The error message as a string.
     */
    public String showErrorMessage(String errorMsg) {
        return errorMsg;
    }

    /**
     * Displays a message to indicate that a task has been successfully added.
     *
     * @param taskList The task list containing the added task.
     * @return A string to indicate that a task has been successfully added.
     */
    public String showTaskAdded(TaskList taskList) {
        return "Got it. I've added this task:\n"
            + "  " + taskList.getList().get(taskList.getSize() - 1).toString() + "\n"
            + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The task list to be displayed.
     * @return The list of tasks as a string.
     */
    public String showTaskList(TaskList taskList) {
        String taskListString = "Here are the tasks in your list:\n";
        ArrayList<Task> list = taskList.getList();
        int start = 1;
        for (Task listItems : list) {
            taskListString += String.format("%d. %s\n", start, listItems.toString());
            start++;
        }
        return taskListString;
    }

    /**
     * Displays a message to indicate that a task has been successfully deleted.
     *
     * @param tasklist The task list containing the deleted task.
     * @param deleted  The deleted task.
     * @return A string to indicate that a task has been successfully deleted.
     */
    public String showTaskDeleted(TaskList tasklist, Task deleted) {
        String message = "Noted. I've removed this task:\n"
                + "  " + deleted + "\n"
                + "Now you have " + tasklist.getSize() + " tasks in the list.";

        return message;
    }

    /**
     * Displays a message to indicate that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A string to indicate that a task has been successfully marked.
     */
    public String showDoneTask(Task task) {
        String message = "Nice! I've marked this task as done:\n"
                + "  " + task.toString();

        return message;
    }


    /**
     * Displays a message to indicate that a task has been marked as undone.
     *
     * @param task The task that has been marked as undone.
     * @return A string to indicate that a task has been successfully unmarked.
     */
    public String showUnmarkTask(Task task) {
        String message = "I've marked this task as undone:\n"
                + "  " + task.toString();

        return message;
    }


    /**
     * Displays tasks that contains the keyWord.
     *
     * @param matchingTasks The task list to search on.
     * @param keyWord       Keyword to be searched.
     * @return A list of matching tasks.
     */
    public String showMatchingTasks(TaskList matchingTasks, String keyWord) {
        ArrayList<Task> taskList = matchingTasks.findTasksByKeyword(keyWord);
        String output = "Here are the matching tasks in your list:\n";
        for (Task task : taskList) {
            output += task.toString() + "\n";
        }
        return output;
    }
}
