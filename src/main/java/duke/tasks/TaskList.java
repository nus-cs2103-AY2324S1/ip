package duke.tasks;

import java.util.ArrayList;

import duke.tasks.Task.Priority;

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

        // Assert that the initial list of tasks provided is not null.
        assert tasks != null : "Initial list of tasks cannot be null";
    }

    /**
     * Adds a task to the task list and prints a confirmation message.
     *
     * @param task The task to be added.
     * @return The confirmation message.
     */
    public String addTask(Task task) {
        tasks.add(task);

        // Assert that the task has been successfully added to the list.
        assert tasks.contains(task) : "Task failed to be added to list";

        return "Got it. I've added this task:\n" + task.getDescription()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the task list and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @return The confirmation message.
     */
    public String deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return "Please enter a valid number.";
        }

        Task task = tasks.get(index);
        tasks.remove(index);
        assert !tasks.contains(task) : "Task failed to be removed from list";
        return "Noted. I've removed this task:\n" + task.getDescription()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Marks a task as done and prints a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     * @return The confirmation message.
     */
    public String markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            return "Please enter a valid number.";
        }

        Task task = tasks.get(index);
        task.markAsDone();
        assert task.isDone() : "Task was not marked as done";
        return "Nice! I've marked this task as done:\n" + task.getDescription();
    }

    /**
     * Marks a task as not done and prints a confirmation message.
     *
     * @param index The index of the task to be marked as not done.
     * @return The confirmation message.
     */
    public String unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return "Please enter a valid number.";
        }

        Task task = tasks.get(index);
        task.unmark();
        assert !task.isDone() : "Task was not marked as not done";
        return "OK, I've marked this task as not done yet:\n" + task.getDescription();
    }

    /**
     * Sets the priority of a task and prints a confirmation message.
     *
     * @param index
     * @param priority
     * @return The confirmation message.
     */
    public String setPriority(int index, Priority priority) {
        if (index < 0 || index >= tasks.size()) {
            return "Please enter a valid number.";
        }

        Task task = tasks.get(index);
        task.setPriority(priority);
        return "OK, I've set the priority of this task to " + priority + ":\n" + task.getDescription();
    }

    /**
     * Prints all the tasks in the task list.
     *
     * @return The list of tasks.
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
     * @return The list of tasks that contain the keyword.
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

