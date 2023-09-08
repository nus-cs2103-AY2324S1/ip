package javai;

/**
 * The Ui class handles user interface interactions and message display.
 */
public class Ui {


    /**
     * Displays a welcome message when the chatbot starts.
     */
    public String displayWelcome() {
        return ("      Hello, I'm JavAI.\n      What can I do for you?\n");
    }

    /**
     * Displays a message when a task is added successfully.
     *
     * @param task The added task.
     * @param tasks The current list of tasks.
     */
    public String printAddTask(Task task, TaskList tasks) {
        return ("Got it. I've added this task:\n"
                + task + "\nNow you have " + tasks.size() + " tasks in the list.");


    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public String printList(TaskList tasks) {
        try {
            String result = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                result += "\n" +  (i + 1) + "." + tasks.get(i).toString();
            }
            return result;
        } catch (JavAiException e) {
            return showLoadingError(e);
        }
    }

    /**
     * Displays a message when a task is deleted successfully.
     *
     * @param task The deleted task.
     * @param tasks The current list of tasks.
     */
    public String printDelete(Task task, TaskList tasks) {

        return ("Noted. I've removed this task:\n"
                + task + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.\n");

    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task marked as done.
     */
    public String printDone(Task task) {
        return ("Nice! I've marked this task as done:\n"
                + task
                + "\n" );
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task marked as not done.
     */
    public String printUndone(Task task) {
        return ("Ok! I've marked this task as not done yet:\n" + task + "\n");
    }

    /**
     * Displays an exit message when the chatbot exits.
     */
    public String exit() {

        return ("      Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays an error message when an exception occurs.
     *
     * @param e The exception that occurred.
     */
    public String showLoadingError(Exception e) {

        return e.getMessage();
    }

    /**
     * Displays a generic message.
     *
     * @param string The message to display.
     */
    public String print(String string) {

        return string;
    }

}
