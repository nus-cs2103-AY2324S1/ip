package duke;

import java.util.ArrayList;
import java.util.List;

/** Task List to store all the tasks. */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Class constructor when there are previously stored tasks.
     * @param tasks list of tasks stored previously.
     * @param ui the UI containing the message responses.
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Class constructor when there are no previously stored tasks.
     * @param ui the UI containing the message responses.
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Returns a string message containing the tasks which match
     * the description inputted by the user.
     * @param description the description inputted by the user.
     * @return a string message containing the filtered tasks.
     */
    public String filterTasks(String description) {
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(description)) {
                filteredTasks.add(task);
            }
        }
        return this.ui.listFoundTasks(filteredTasks);
    }

    /**
     * Saves the tasks currently tracked by Duke.
     * @param storage the storage class which stores the tasks.
     */
    public void save(Storage storage) {
        storage.save(this.tasks);
    }

    /**
     * Lists the tasks currently tracked by Duke.
     * @return a string message containing the list of tasks tracked.
     */
    public String listTasks() {
        return this.ui.listMessage(tasks);
    }

    /**
     * Returns a string message indicating that the task is successfully
     * marked as done.
     * @param taskNumber the index of the task to be marked as done.
     * @return a string message indicating that the task is marked as done.
     */
    public String markTaskAsDone(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.markAsDone();
        return this.ui.markTaskAsDoneMessage(task);
    }

    /**
     * Returns a string message indicating that the task is marked as undone.
     * @param taskNumber the index of the task to be marked as done.
     * @return a string message indicating that the task is marked as undone.
     */
    public String unmarkTask(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.unmark();
        return this.ui.unmarkTaskMessage(task);
    }

    /**
     * Deletes a task from the stored list.
     * @param taskNumber the index of the task to be deleted.
     * @return a string message indicating that the task is deleted.
     */
    public String deleteTask(int taskNumber) {
        Task task = this.tasks.remove(taskNumber);
        return this.ui.deleteTaskMessage(task, this.tasks.size());
    }

    /**
     * Adds a task into the list.
     * @param task the Task to be added.
     * @return a string message indicating that the task is added.
     */
    public String addTask(Task task) {
        int startOfDescriptionIndex = 7;
        if (this.containsTask(task)) {
            return this.ui.duplicateTaskMessage(task, startOfDescriptionIndex);
        }
        this.tasks.add(task);
        return this.ui.addTaskMessage(task, this.tasks.size(), startOfDescriptionIndex);
    }

    private boolean containsTask(Task task) {
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).equals(task)) {
                return true;
            }
        }
        return false;
    }
}
