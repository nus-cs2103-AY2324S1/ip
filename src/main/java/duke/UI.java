package duke;

import java.util.ArrayList;

/**
 * A class to handle interactions with the user.
 */
public class UI {
    private String name;

    /**
     * Constructor for the UI class.
     *
     * @param name Name of the chatbot.
     */
    public UI(String name) {
        this.name = name;
    }

    /**
     * Prints a welcome message.
     */
    public String getWelcomeMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm " + this.name + "\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }

    /**
     * Prints a goodbye message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a task added message.
     *
     * @param task      Task added.
     * @param taskCount Total number of tasks.
     * @return
     */
    public String getTaskAddedMessage(Task task, int taskCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(task + "\n");
        sb.append(this.printTaskCount(taskCount));
        return sb.toString();
    }

    /**
     * Prints a task deleted message.
     *
     * @param task      Task deleted.
     * @param taskCount Total number of tasks.
     * @return
     */
    public String getTaskDeletedMessage(Task task, int taskCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task + "\n");
        sb.append(this.printTaskCount(taskCount));
        return sb.toString();
    }

    /**
     * Prints a task marked message.
     *
     * @param task Task marked.
     * @return
     */
    public String getTaskMarkedMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task);
        return sb.toString();
    }

    /**
     * Prints a task unmarked message.
     *
     * @param task Task unmarked.
     * @return
     */
    public String getTaskUnmarkedMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(task + "\n");
        return sb.toString();
    }

    /**
     * Prints the tasks on a date.
     *
     * @param tasks List of tasks on a date
     * @return
     */
    public String getTasksOn(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Prints the tasks matching a keyword.
     *
     * @param tasks List of tasks matching a keyword
     * @return
     */
    public String getTasksMatching(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString();
    }

    public String getAllTasksMessage(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }

    /**
     * Prints a loading error message.
     *
     * @return
     */
    public String getLoadingErrorMessage() {
        return "Stored data could not be loaded";
    }

    /**
     * Prints a task count message.
     *
     * @param taskCount Total number of tasks.
     */
    private String printTaskCount(int taskCount) {
        return "Now you have " + taskCount + (taskCount == 1 ? " task" : " tasks") + " in the list.\n";
    }
}
