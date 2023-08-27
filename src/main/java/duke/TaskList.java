package duke;

import java.util.ArrayList;

/** Class which contains the task list. */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Class constructor specifying the task list (since tasks are stored initially) and the UI
     * @param tasks an ArrayList storing tasks
     * @param ui the UI dealing with user interactions
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Class constructor specifying the UI
     * @param ui the UI dealing with user interactions
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Saves the tasks on the local hard disk.
     * @param storage the storage class which deals with loading and saving tasks.
     */
    public void save(Storage storage) {
        storage.save(this.tasks);
        this.ui.exitMessage();
    }

    /**
     * Prints out the list of tasks tracked by Duke.
     */
    public void listTasks() {
        this.ui.listMessage(tasks);
    }

    /**
     * Marks the task as done.
     * @param taskNumber index to identify the task.
     */
    public void markTaskAsDone(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.markAsDone();
        this.ui.markTaskAsDoneMessage(task);
    }

    /**
     * Unmarks the task as incomplete.
     * @param taskNumber index to identify the task.
     */
    public void unmarkTask(int taskNumber) {
        Task task = this.tasks.get(taskNumber);
        task.unmark();
        this.ui.unmarkTaskMessage(task);
    }

    /**
     * Deletes the task from Duke.
     * @param taskNumber index to identify the task.
     */
    public void deleteTask(int taskNumber) {
        Task task = this.tasks.remove(taskNumber);
        this.ui.deleteTaskMessage(task, this.tasks.size());
    }

    /**
     * Adds the task to Duke.
     * @param task task to be added to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.ui.addTaskMessage(task, this.tasks.size());
    }
}
