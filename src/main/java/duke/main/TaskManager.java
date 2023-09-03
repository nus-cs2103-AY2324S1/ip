package duke.main;

import duke.exceptions.InvalidArgumentException;
import duke.main.UI;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * The TaskManager class manages a list of tasks and provides methods to interact with the tasks.
 * It can add, list, mark, unmark, and delete tasks from the list.
 */
public class TaskManager {
    private ArrayList<Task> list;
    private int index;
    private int numOfTasks;
    private UI ui;

    /**
     * Constructs a TaskManager with an empty task list.
     * Initializes the index, number of tasks, and UI.
     */

    public TaskManager() {
        this.list = new ArrayList<>();
        this.index = 0;
        this.numOfTasks = 0;
        this.ui = new UI();
    }

    /**
     * Constructs a TaskManager with an existing list of tasks.
     * Initializes the index, number of tasks, and UI.
     *
     * @param tasks The list of tasks to initialize the TaskManager with.
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.list = tasks;
        this.numOfTasks = this.list.size();
        this.index = this.numOfTasks - 1;
        this.ui = new UI();
    }

    /**
     * Adds a task to the list and displays change using the UI.
     *
     * @param task The task to be added.
     */
    public void add (Task task) {
        this.list.add(task);
        this.numOfTasks += 1;
        String taskName = task.getTaskName();
        ui.addTask(taskName, numOfTasks);
    }

    /**
     * Lists all tasks in the list using the UI.
     */
    public void list() {
        ui.displayList(list, numOfTasks);
    }

    /**
     * Marks a task as done by its index in the list and updates the UI.
     *
     * @param index The index of the task to be marked.
     * @throws InvalidArgumentException If the index is out of range.
     */
    public void mark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + "duke.tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.mark();
        ui.markTask(task.getTaskName());
    }


    /**
     * Marks a task as not done by its index in the list and updates the UI.
     *
     * @param index The index of the task to be unmarked.
     * @throws InvalidArgumentException If the index is out of range.
     */
    public void unmark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " duke.tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.unmark();
        ui.unMarkTask(task.getTaskName());
    }

    /**
     * Removes a task from the ArrayList of tasks.
     *
     * @param index The index of the task to be unmarked.
     * @throws InvalidArgumentException If the index is out of range.
     */
    public void delete(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " duke.tasks.");
        }
        numOfTasks -= 1;
        Task removedTask = list.get(index);
        list.remove(index);
        ui.deleteTask(removedTask.getTaskName(), numOfTasks);
    }

    /**
     * Retrieves the list of tasks managed by the TaskManager.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }
}
