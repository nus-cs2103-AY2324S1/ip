package javai;

/**
 * The Ui class handles user interface interactions and message display.
 */
public class Ui {

    String line = "     ____________________________________________________________";

    /**
     * Displays a welcome message when the chatbot starts.
     */
    public void welcome() {
        System.out.println(line + "\n      Hello, I'm JavAI.\n      What can I do for you?\n" + line);
    }

    /**
     * Displays a horizontal line to separate sections in the output.
     */
    public void displayLine() {
        System.out.println(line);
    }

    /**
     * Displays a message when a task is added successfully.
     *
     * @param task The added task.
     * @param tasks The current list of tasks.
     */
    public void printAddTask(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void printList(TaskList tasks) {
        try {
            displayLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
            displayLine();
        } catch (JavAIException e) {
            showLoadingError(e);
        }
    }

    /**
     * Displays a message when a task is deleted successfully.
     *
     * @param task The deleted task.
     * @param tasks The current list of tasks.
     */
    public void printDelete(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Noted. I've removed this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task marked as done.
     */
    public void printDone(Task task) {
        displayLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        displayLine();
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task marked as not done.
     */
    public void printUndone(Task task) {
        displayLine();
        System.out.println("Ok! I've marked this task as not done yet:\n" + task);
        displayLine();
    }

    /**
     * Displays an exit message when the chatbot exits.
     */
    public void exit() {

        System.out.println(line + "\n      Bye. Hope to see you again soon!\n" + line);
    }

    /**
     * Displays an error message when an exception occurs.
     *
     * @param e The exception that occurred.
     */
    public void showLoadingError(Exception e) {

        System.out.println(e.getMessage());
    }

    /**
     * Displays a generic message.
     *
     * @param string The message to display.
     */
    public void print(String string) {

        System.out.println(string);
    }

}
