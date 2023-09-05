package chadbod;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the ChadBod application.
 */
public class TaskList {

    private static final int TASKLIST_DISPLAY_OFFSET = 1;
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class, initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Get the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Get a task at a specific index in the task list.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Add a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Remove a task from the task list at a specific index.
     *
     * @param i The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    /**
     * Convert the task list to a string representation.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        if (tasks.isEmpty()) {
            output = new StringBuilder("There are no tasks in your list!");
        } else {
            output.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                output.append(String.format("%d.%s\n", i + TASKLIST_DISPLAY_OFFSET, tasks.get(i)));
            }
        }
        return String.valueOf(output);
    }

    /**
     * Find tasks in the task list that contain a specific keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A new TaskList containing tasks that match the keyword.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }
}
