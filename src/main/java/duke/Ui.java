package duke;

import java.util.ArrayList;
import java.util.List;

/** The UI managing the message responses of Duke. */
public class Ui {

    /**
     * Lists the tasks that matches the user input.
     * @param filteredTasks the filtered list of tasks that matches the user input.
     * @return a String message containing the list of tasks that matches the user input.
     */
    public String listFoundTasks(List<Task> filteredTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, filteredTasks.get(i).toString()));
        }
        return sb.toString();
    }

    /**
     * Lists the tasks currently stored in Duke.
     * @param tasks the list of tasks currently stored.
     * @return a string message containing the list of tasks currently stored in Duke.
     */
    public String listMessage(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(String.format("%d.%s%n", i + 1, task));
        }
        return sb.toString();
    }

    /**
     * Returns a string message indicating that the task is successfully marked as done.
     * @param task the task that is marked as done.
     * @return a string message indicating that the task is marked as done.
     */
    public String markTaskAsDoneMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(String.format("%s%n", task));
        return sb.toString();
    }

    /**
     * Returns a string message indicating that the task is unmarked as incomplete.
     * @param task the task to be unmarked as incomplete.
     * @return a string message indicating that the task is unmarked as incomplete.
     */
    public String unmarkTaskMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(String.format("%s%n", task));
        return sb.toString();
    }

    /**
     * Returns a string message indicating that the task is deleted.
     * @param task the task to be deleted.
     * @param numOfTasks the number of tasks left.
     * @return a string message indicating that the task is deleted and the number of tasks left.
     */
    public String deleteTaskMessage(Task task, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(String.format("%s\n", task));
        sb.append(String.format("Now you have %d tasks in the list.%n", numOfTasks));
        return sb.toString();
    }

    /**
     * Returns a string message indicating that the task is added.
     * @param task the task that is added.
     * @param numOfTasks the number of tasks now stored in Duke.
     * @return a string message indicating that the task is added and the number of tasks stored.
     */
    public String addTaskMessage(Task task, int numOfTasks, int startOfDescriptionIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(String.format("%s\n", task.toString().substring(startOfDescriptionIndex)));
        sb.append(String.format("Now you have %d tasks in the list.%n", numOfTasks));
        return sb.toString();
    }

    public String duplicateTaskMessage(Task task, int startOfDescriptionIndex) {
        return String.format("%s is already added before!", task.toString().substring(startOfDescriptionIndex));
    }
}
