package duke.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Duke application.
 */
public class TaskList {

    /** The list of tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n" + task.getDescription()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the task list and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     */
    public String deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return "Please enter a valid number.";
        }

        Task task = tasks.get(index);
        tasks.remove(index);
        return "Noted. I've removed this task:\n" + task.getDescription()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Marks a task as done and prints a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     */
    public String markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            return "Please enter a valid number.";
        }

        Task task = tasks.get(index);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task.getDescription();
    }

    /**
     * Marks a task as not done and prints a confirmation message.
     *
     * @param index The index of the task to be marked as not done.
     */
    public String unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return "Please enter a valid number.";
        }

        Task task = tasks.get(index);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task.getDescription();
    }

    /**
     * Prints all the tasks in the task list.
     */
    public String list() {
        if (tasks.size() == 0) {
            return "You have no tasks in your list. Add some tasks!";
        }

        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            s = s + (i + 1) + ". " + task.getDescription() + "\n";
        }
        return s;
    }

    /**
     * Finds tasks that contain a given keyword and prints them.
     *
     * @param keyword The keyword to be searched for.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }

        if (foundTasks.size() == 0) {
            return "No matching tasks found.";
        }

        String s = "Here are the matching tasks in your list:";
        for (int i = 0; i < foundTasks.size(); i++) {
            Task task = foundTasks.get(i);
            s = s + (i + 1) + ". " + task.getDescription();
        }
        return s;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

