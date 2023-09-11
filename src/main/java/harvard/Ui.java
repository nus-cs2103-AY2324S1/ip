package harvard;
/**
 * Represents a user interface.
 */

public class Ui {
    /**
     * Displays the welcome message.
     */
    public String displayWelcome() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Displays a line.
     * @return A line.
     */
    public String displayLine() {
        return "____________________________________________________________";
    }
    /**
     * Displays the bye message.
     * @return The bye message.
     */
    public String displayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the add task message.
     * @param task
     * @param tasks
     * @return The add task message.
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Displays the list of tasks.
     * @param tasks
     * @return The list of tasks.
     */
    public String showList(TaskList tasks) {
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n" + (i + 1) + "." + tasks.get(i);
        }
        return result;
    }

    /**
     * Displays the delete message.
     * @param task
     * @param tasks
     * @return The delete message.
     */
    public String showDelete(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Displays the done message.
     * @param task
     * @return The done message.
     */
    public String showDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays the undone message.
     * @param task
     * @return The undone message.
     */

    public String showUndone(Task task) {
        return "Ok! I've marked this task as not done yet:";
    }

    /**
     * Displays the find message.
     * @param matchingTasks
     * @return The find message.
     */

    public String showFind(TaskList matchingTasks) {
        String result = "Here are the matching tasks in your list:";
        for (int i = 0; i < matchingTasks.size(); i++) {
            result += "\n" + (i + 1) + "." + matchingTasks.get(i);
        }
        return result;
    }

    public String showClearAll(String type) {
        return "Noted. I've removed all " + type + " tasks.";
    }

    /**
     * Displays the error message.
     * @param e
     * @return The error message.
     */
    public String displayError(DukeException e) {
        return e.getMessage();
    }
}
