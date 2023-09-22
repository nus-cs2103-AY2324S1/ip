package jarvis.ui;

import java.util.ArrayList;

import jarvis.task.Deadline;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;


/**
 * Represents the user interface for interacting with the user.
 * The class is responsible for displaying messages to the user and reading user input.
 */
public class Ui {

    /**
     * Initializes the Ui class and prepares it to read user input.
     */
    public Ui() {
    }

    /**
     * Displays the provided texts to the user surrounded by horizontal lines.
     *
     * @param text The messages to be displayed to the user.
     */
    public String display(String... text) {
        assert text != null : "Text to be displayed should not be null";

        String response = "";
        for (String i : text) {
            response += i + "\n";
        }
        return response;
    }

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return display("Hello Master! I'm Jarvis, your AI personal assistant.", "What can I do for you?");
    }

    /**
     * Displays a farewell message to the user.
     */
    public String farewell() {
        return display("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that has been added.
     * @param tasks The current task list.
     */
    public String displayAddedTask(Task task, TaskList tasks) {
        assert task != null : "Task to be displayed should not be null";
        assert tasks != null : "Task list should not be null";

        return display("Got it. I've added this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param tasks The current task list.
     */
    public String displayDeletedTask(Task task, TaskList tasks) {
        assert task != null : "Task to be displayed should not be null";
        assert tasks != null : "Task list should not be null";

        return display("Got it. I've removed this task:", task.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that has been marked.
     */
    public String displayMarkedTask(Task task) {
        assert task != null : "Task to be displayed should not be null";

        return display("Nice! I've marked this task as done:", task.toString());
    }

    /**
     * Displays a message confirming that a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public String displayUnmarkedTask(Task task) {
        assert task != null : "Task to be displayed should not be null";

        return display("OK, I've marked this task as not done yet:", task.toString());
    }

    /**
     * Displays a message informing the user that their task list is currently empty.
     */
    public String displayEmptyList() {
        return display("You currently have no tasks in your list.");
    }

    /**
     * Displays all the tasks in the user's task list.
     *
     * @param tasks The user's current task list.
     */
    public String displayList(TaskList tasks) {
        assert tasks != null : "Task list should not be null";

        String response = "";
        response += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            response += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return response;
    }

    /**
     * Displays tasks that match a search query.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public String displayMatchingTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";

        if (tasks.isEmpty()) {
            return display("No tasks matched your search query.");
        } else {
            String response = "";
            response += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                response += (i + 1) + "." + tasks.get(i) + "\n";
            }
            return response;
        }
    }

    /**
     * Displays the nearest upcoming deadline that is not done yet.
     *
     * @param upcomingDeadline The nearest deadline that is not done yet.
     */
    public String displayUpcomingDeadline(Deadline upcomingDeadline) {
        if (upcomingDeadline == null) {
            return display("There are no upcoming deadlines that are not done yet.");
        } else {
            return display("The nearest deadline that needs to be done is", upcomingDeadline.toString());
        }
    }
}
