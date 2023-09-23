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
     * Displays a goodbye message when the user exits the application.
     */
    public String showByeMessage() {
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Displays a list of tasks.
     *
     * @param taskList The list of tasks to display.
     */
    public String showTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There are no tasks.";
        }
        return "Here are the tasks in your list:" + "\n" + taskList.toString();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     * @return A message to the user that the task is done.
     */
    public String markedMessage(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task.toString();
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String unmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:" + "\n" + task.toString();
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task       The task that was added.
     * @param numOfTasks The number of tasks in the list after adding.
     */
    public String addTaskMessage(Task task, int numOfTasks) {
        String taskOrTasks = numOfTasks == 1 ? "task" : "tasks";
        return "Got it. I've added this task:" + "\n" + task.toString() + "\n"
                + "Now you have " + numOfTasks + " " + taskOrTasks + " in the list.";
    }

    /**
     * Displays a message when a task is deleted from the task list.
     *
     * @param task       The task that was deleted.
     * @param numOfTasks The number of tasks in the list after deletion.
     */
    public String deleteTaskMessage(Task task, int numOfTasks) {
        String taskOrTasks = numOfTasks == 1 ? "task" : "tasks";
        return "Noted. I've removed this task:" + "\n" + task.toString() + "\n"
                + "Now you have " + numOfTasks + " " + taskOrTasks + " in the list.";
    }

    /**
     * Displays a message for an invalid user input or command.
     */
    public String invalidTaskMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Displays a message when the user searches for a task.
     */
    public String findTaskMessage(TaskList taskList, String keyword) {
        if (taskList.isEmpty()) {
            return "There are no tasks that contain the keyword: " + keyword;
        }

        return "Here are the matching tasks in your list:" + "\n" + taskList.toString();
    }

    /**
     * Displays a message to show the before and after a task is edited.
     *
     * @param newTask The tasks in the list after editing.
     */
    public String editTaskMessage(Task newTask) {
        return "Your task has been updated to: " + "\n" + newTask.toString();
    }
}
