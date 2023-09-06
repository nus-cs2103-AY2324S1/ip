package duke;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manipulate them.
 */
public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    /**
     * Initializes a new TaskList with a list of tasks and a user interface.
     *
     * @param tasks The list of tasks to be managed.
     * @param ui    The user interface for interaction.
     */
    public TaskList(List<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Initializes a new TaskList with an empty list of tasks and a user interface.
     *
     * @param ui The user interface for interaction.
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index and displays a confirmation message.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task task = tasks.get(index);
        this.tasks.remove(index);
        ui.deleteTaskMessage(task, tasks.size());
    }

    /**
     * Marks a task as done in the task list at the specified index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as not done in the task list at the specified index.
     *
     * @param index The index of the task to be marked as not done.
     */

    public void markTaskAsUnDone(int index) {
        this.tasks.get(index).markAsUnDone();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int numOfTasks() {
        return this.tasks.size();
    }

    /**
     * Checks if there are tasks in the task list.
     *
     * @return Boolean depending on whether the task list is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Gets a list of tasks with descriptions that matches the specified keyword.
     *
     * @param keyword The specified keyword to match.
     * @return A list of tasks that contain the keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList tempTaskList = new TaskList(ui);
        for (Task task: tasks) {
            if (task.description.contains(keyword)) {
                tempTaskList.addTask(task);
            }
        }
        return tempTaskList;
    }

    /**
     * Returns a string representation of the task list, including task numbers and their details.
     *
     * @return A formatted string representing the task list.
     */
    @Override
    public String toString() {
        StringBuffer outputString = new StringBuffer();
        for(int i = 0; i < tasks.size(); i++) {
            outputString.append(i + 1);
            outputString.append(". ");
            outputString.append(tasks.get(i).toString());
            outputString.append("\n");
        }
        return outputString.toString();
    }
}

