package duke;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods to manipulate the list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList object with overloading.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list and displays a confirmation message.
     *
     * @param task The task to be added.
     * @param ui The user interface object.
     * @return A confirmation message for adding the task.
     */
    public String addTask(Task task, Ui ui) {
        tasks.add(task);
        return ui.showAddTask(task, tasks.size());
    }

    /**
     * Removes a task from the task list by its index and displays a confirmation message.
     *
     * @param index The index of the task to be removed.
     * @param ui The user interface object.
     * @return A confirmation message for removing the task.
     */
    public String removeTask(int index, Ui ui) {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return ui.showDeletedTask(removedTask, tasks.size());
    }

    /**
     * Marks a task as done by its index and displays a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     * @param ui The user interface object.
     * @return A confirmation message for marking the task as done.
     */
    public String markTask(int index, Ui ui) {
        Task markedTask = tasks.get(index);
        markedTask.markAsDone();
        return ui.showDone(markedTask);
    }

    /**
     * Marks a task as not done by its index and displays a confirmation message.
     *
     * @param index The index of the task to be marked as not done.
     * @param ui The user interface object.
     * @return A confirmation message for marking the task as not done.
     */
    public String unmarkTask(int index, Ui ui) {
        Task unmarkedTask = tasks.get(index);
        unmarkedTask.markAsUndone();
        return ui.showUndone(unmarkedTask);
    }

    /**
     * Calculates the size of the task list
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task list, including task numbers.
     *
     * @return A formatted string displaying all tasks in the list with task numbers.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Finds and returns tasks containing the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the keyword.
     */
    public ArrayList<Task> findTasksWithKeyword(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
