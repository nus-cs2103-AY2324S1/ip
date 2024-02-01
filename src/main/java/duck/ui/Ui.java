package duck.ui;
import java.util.ArrayList;
import java.util.Scanner;

import duck.task.Task;
import duck.task.TaskList;

/**
 * Responsible for handling Input Output tasks in the application.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs Ui Object.
     */
    public Ui() {
    }
    /**
     * Displays a list of tasks to the user.
     *
     * @param taskList The TaskList containing tasks to be displayed.
     */
    public String display(TaskList taskList) {
        int count = 0;
        int serial = 1;
        StringBuilder result = new StringBuilder();
        if (taskList.size() == 0) {
            return "There are no tasks in your list.";
        }
        result.append("Here are all the tasks in your list: \n");
        while (count < taskList.size()) {
            result.append(count + 1).append(".").append(taskList.get(count)).append("\n");
            count++;
            serial++;
        }
        return result.toString();
    }
    /**
     * Displays an exit message.
     */
    public String exit() {
        return "Bye. Hope to see you again soon !";
    }

    /**
     * Displays a confirmation message when some task gets added.
     * @param t
     * @param taskList
     */
    public String echoAdd(Task t, TaskList taskList) {
        StringBuilder result = new StringBuilder();
        extracted(t, result);
        result.append("Now you have ").append(taskList.size()).append(" tasks in the list.\n");
        return result.toString();
    }

    private static void extracted(Task t, StringBuilder result) {
        result.append("Got it. I've added this task:\n");
        result.append("  ").append(t).append("\n");
    }

    /**
     * It generates a human-readable string representation of the search results.
     * If there are matching tasks in the provided list, it formats them as a numbered list.
     * If the list is empty, it returns a message indicating that no matching tasks were found.
     * @param list The list of tasks to display as search results.
     * @return A formatted string containing either the matching tasks or a message if tsk not found.
     */

    public String displaySearchResults(ArrayList<Task> list) {
        StringBuilder result = new StringBuilder();
        if (list.isEmpty()) {
            return "I couldn't find any matching tasks in your list.";
        }
        result.append("Here are the matching tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            result.append(i + 1).append(".").append(list.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * It gives a human-readable string representation of the scheduled tasks for a given day.
     * It formats the scheduled tasks in a numbered list.
     * If scheduled tasks are not found,
     * it returns a message indicating no scheduled tasks are present for the current day.
     * @param ls The list of tasks to display as scheduled tasks.
     * @return A formatted string containing either the scheduled tasks or a message depicting no tasks.
     */
    public String displayScheduledTasks(ArrayList<Task> ls) {
        StringBuilder result = new StringBuilder();
        String message = "Hi!, Following are your scheduled Tasks for the day\n";
        result.append(message);
        if (ls.size() == 0) {
            return "No Scheduled Tasks for the Date";
        }
        for (int i = 0; i < ls.size(); i++) {
            result.append(i + 1).append(".").append(ls.get(i)).append("\n");
        }
        return result.toString();
    }

}
