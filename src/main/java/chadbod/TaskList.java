package chadbod;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in the ChadBod application.
 */
public class TaskList {

    private static final int TASKLIST_DISPLAY_OFFSET = 1;
    private ArrayList<Task> tasks;

    /**
     * Constructs an instance of the TaskList class with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns a task at a specific index in the task list.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        assert t != null : "No task passed to addTask method.";
        tasks.add(t);
    }

    /**
     * Returns a task from the task list at a specific index after removing it from the list.
     *
     * @param i The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int i) {
        return tasks.remove(i);
    }
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
     * Returns a list of tasks that contain a specific keyword in their description.
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
