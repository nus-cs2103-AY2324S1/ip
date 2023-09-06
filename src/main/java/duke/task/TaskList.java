package duke.task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import duke.Storage;
import duke.Ui;

/**
 * Represents a list of tasks and provides methods to manage tasks.
 */
public class TaskList {
    private int taskNum = 0;
    private Storage store = new Storage();
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList by loading tasks from storage (if available).
     * If storage is not available, displays a message indicating no tasks.
     */
    public TaskList() {
        try {
            this.tasks = store.loadTasks();
        } catch (FileNotFoundException e) {
            Ui.noTaskList();
        }
        this.taskNum = this.tasks.size();
    }

    /**
     * Adds a task to the task list and updates storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskNum += 1;
        store.saveTasks(this.tasks);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int taskCount() {
        return this.taskNum;
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks a task as done at the specified index and updates storage.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        store.saveTasks(this.tasks);
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as not done at the specified index and updates storage.
     *
     * @param index The index of the task to mark as not done.
     */
    public void unMarkTask(int index) {
        store.saveTasks(this.tasks);
        this.tasks.get(index).markAsNotDone();
    }

    /**
     * Deletes a task at the specified index, updates task count, and updates storage.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.taskNum -= 1;
        store.saveTasks(this.tasks);
    }

    /**
     * Searches for tasks containing the specified keyword and displays the found tasks.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task tsk : this.tasks) {
            if (tsk.getDesc().contains(keyword)) {
                foundTasks.add(tsk);
            }
        }
        Ui.findTasks(foundTasks);
    }
}
