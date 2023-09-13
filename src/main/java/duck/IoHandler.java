package duck;
import java.util.ArrayList;
import java.util.Scanner;

import duck.task.Task;
import duck.task.TaskList;

/**
 * Responsible for handling Input Output tasks in the application.
 */
public class IoHandler {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs IoHandler Object.
     */
    public IoHandler() {
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
            return "There are no tasks in your lists.";
        }
        result.append("Here are all the tasks in your list: \n");
        while (count < taskList.size()) {
            result.append(serial).append(".").append(taskList.get(count)).append("\n");
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

    public String displaySearchResults(ArrayList<Task> list) {
        StringBuilder result = new StringBuilder();
        if (list.isEmpty()) {
            return "I couldn't find any matching tasks in your lists.";
        }
        result.append("Here are the matching tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            result.append(i + 1).append(".").append(list.get(i)).append("\n");
        }
        return result.toString();
    }
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
