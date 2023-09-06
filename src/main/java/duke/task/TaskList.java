package duke.task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import duke.Storage;
import duke.ui.Ui;

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
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return A message confirming the addition of the task.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        this.taskNum += 1;
        store.saveTasks(this.tasks);
        return Ui.addTask(task, taskNum);
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
     * Marks a task as done in the task list.
     *
     * @param index The index of the task to mark as done.
     * @return A message confirming the task has been marked as done.
     */
    public String markTask(int index) {
        store.saveTasks(this.tasks);
        this.tasks.get(index).markAsDone();
        return Ui.markTask(this.tasks.get(index));
    }

    /**
     * Marks a task as not done in the task list.
     *
     * @param index The index of the task to mark as not done.
     * @return A message confirming the task has been marked as not done.
     */
    public String unMarkTask(int index) {
        store.saveTasks(this.tasks);
        this.tasks.get(index).markAsNotDone();
        return Ui.unMarkTask(this.tasks.get(index));
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     * @return A message confirming the deletion of the task.
     */
    public String deleteTask(int index) {
        String desc = this.tasks.get(index).toString();
        this.tasks.remove(index);
        this.taskNum -= 1;
        store.saveTasks(this.tasks);
        return Ui.deleteTask(desc, taskNum);
    }

    /**
     * Finds tasks containing a specific keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A message listing tasks that match the keyword.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task tsk : this.tasks) {
            if (tsk.getDesc().contains(keyword)) {
                foundTasks.add(tsk);
            }
        }
        return Ui.findTasks(foundTasks);
    }
}
