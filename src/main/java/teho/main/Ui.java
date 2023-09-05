package teho.main;
import java.lang.StringBuilder;
/**
 * Deals with interactions with the user through generating messages.
 */

public class Ui {
    /**
     * Generates hello message when TehO chatbot starts.
     *
     * @return String representation of hello message
     */
    public static String generateHelloMessage() {
        return "Hello! I'm TehO \nWhat can I do for you?";
    }

    /**
     * Generates goodbye message when user types bye and TehO chatbot exits.
     *
     * @return String representation of bye message.
     */
    public static String generateGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates message containing list of tasks.
     *
     * @param taskList List of tasks to be generated.
     * @return String representation of list of tasks generated.
     */
    public static String generateList(TaskList taskList) {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            str += (i + 1) + ". " + task.toString() + "\n";;
        }
        return str;
    }

    /**
     * Generates message to show that task is marked as done.
     *
     * @param task Task marked as done.
     * @return String representation of marked task message.
     */
    public static String generateMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Generates message to show that task is marked as undone.
     *
     * @param task Task marked as undone.
     * @return String representation of unmarked task message.
     */
    public String generateUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Generates message to show that ToDo task is added to list.
     *
     * @param task Task added to list.
     * @param taskList List of tasks that added task.
     * @return String representation of add ToDo task message.
     */
    public String generateAddToDoMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.getSize() + " task(s) in the list.";
    }

    /**
     * Generates message to show that Deadline task is added to list.
     *
     * @param task Task added to list.
     * @param taskList List of tasks that added task.
     * @return String representation of add Deadline task message.
     */
    public String generateAddDeadlineMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.getSize() + " task(s) in the list.";
    }

    /**
     * Generates message to show that Event task is added to list.
     *
     * @param task Task added to list.
     * @param taskList List of tasks that added task.
     * @return String representation of add Event task message.
     */
    public String generateAddEventMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.getSize() + " task(s) in the list.";
    }

    /**
     * Generates message to show that task is deleted from list.
     *
     * @param task Task deleted from list.
     * @param taskList List of tasks to delete task from.
     * @return String representation of delete task message.
     */
    public String generateDeleteMessage(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.getSize() + " task(s) in the list.";
    }

    /**
     * Generates message to show that there is a loading error.
     *
     * @return String representation of loading error message.
     */
    public String showLoadingError() {
        return "OHNO! Loading error!";
    }

    /**
     * Generates message containing list of tasks that matches the user's command.
     *
     * @param toMatch User's command to be matched with tasks in list.
     * @param taskList List of tasks to be matched from.
     * @return String representation of list of tasks that matches user's command.
     */
    public String generateFindMessage(String toMatch, TaskList taskList) {
        String str = "Here are the matching tasks in your list:\n";
        int count = 0;
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            String stringTask =  task.toString();
            if (stringTask.contains(toMatch)) {
                count++;
                str += count + ". " + stringTask + "\n";
            }
        }
        if (count == 0) {
            str += "There is no matching task:(";
        }
        return str;
    }
}
