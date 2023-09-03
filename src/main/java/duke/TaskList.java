package duke;
import java.util.ArrayList;

import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a list of tasks in Duke. Provides methods to manipulate and access the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {

        tasks = new ArrayList<>();
    }

    /**
     * Initializes the task list with given tasks.
     *
     * @param tasks An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task in the list.
     */
    public void mark(int taskIndex) {

        tasks.get(taskIndex).mark();
    }

    /**
     * Retrieves a task based on its index.
     *
     * @param taskIndex The index of the task in the list.
     * @return The Task object at the given index.
     */
    public Task getTask(int taskIndex) {

        return tasks.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return An integer representing the size of the list.
     */
    public int getSize() {

        return this.tasks.size();
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {

        return this.tasks;
    }

    /**
     * Unmarks a task (i.e., sets it as not done).
     *
     * @param taskIndex The index of the task in the list.
     */
    public void unmark(int taskIndex) {

        tasks.get(taskIndex).unmark();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {

        tasks.add(task);
    }

    /**
     * Deletes a task from the list and returns it.
     *
     * @param taskNum The index of the task in the list.
     * @return The Task object that was removed.
     */
    public Task deleteTask(int taskNum) {
        Task tem = tasks.get(taskNum);
        tasks.remove(taskNum);
        return tem;
    }

    /**
     * Finds tasks by keyword and displays them using the UI.
     *
     * @param keyword The search keyword.
     * @param ui The UI object to display results.
     */
    public String findTasks(String keyword, Ui ui) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return ui.showFoundTasks(matchedTasks);
    }
}
