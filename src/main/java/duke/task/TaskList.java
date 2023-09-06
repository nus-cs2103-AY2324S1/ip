package duke.task;

import java.util.ArrayList;

import duke.DukeException;
import duke.Ui;


/**
 * Represents a list of tasks and provides operations to manage them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty `TaskList`.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a `TaskList` with a given list of tasks.
     *
     * @param tasks The list of tasks to initialize the `TaskList`.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list and displays a confirmation message.
     *
     * @param task The task to be added.
     * @param ui   The user interface for displaying messages.
     * @return A confirmation message as a string.
     */
    public String addTask(Task task, Ui ui) {
        tasks.add(task);
        return ui.showTaskAddedMessage(task, tasks.size());
    }

    /**
     * Deletes a task from the task list by its index and displays a confirmation message.
     *
     * @param index The index of the task to be deleted (1-based).
     * @param ui    The user interface for displaying messages.
     * @return A confirmation message as a string.
     */
    public String deleteTask(int index, Ui ui) {
        Task removedTask = tasks.remove(index - 1);
        return ui.showTaskRemoveMessage(removedTask, tasks.size());
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The task at the specified index.
     * @throws DukeException If the index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @param ui      The Ui instance to display the results.
     * @return A message containing matching tasks as a string.
     */
    public String findTasks(String keyword, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            // No matching tasks found
            return ui.showNoMatchingTasksMessage();
        } else {
            // Matching tasks found, display them to the user
            String response = ui.showFindTaskMessage();
            for (int i = 0; i < matchingTasks.size(); i++) {
                response += i + 1 + ". " + matchingTasks.get(i) + "\n";
            }
            return response;
        }
    }
    /**
     * Gets the total number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int getTotalTasks() {
        return tasks.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Lists all tasks in the task list, displaying them in a numbered format.
     *
     * @return A message containing all tasks as a string.
     * @throws DukeException If the task list is empty.
     */
    public String listTask() throws DukeException {
        if (this.getTotalTasks() == 0) {
            return "List is empty!";
        } else {
            String response = "Here are the tasks in your list:\n";
            for (int item = 0; item < this.getTotalTasks(); item++) {
                response += item + 1 + ". " + this.tasks.get(item) + "\n";
            }
            return response;
        }
    }
}
