package duke.task;

import duke.Storage;
import duke.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks that can be interacted with
 */
public class TaskList {
    private List<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList object
     * @param storage Storage where this list is saved
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
        loadTasks();
    }

    /**
     * Adds a task to this TaskList object
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    /**
     * Removes a task from this TaskList object
     * @param index Position of the task to be removed
     * @throws DukeException Thrown DukeException if there is no such task of that index
     */
    public void removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index.");
        }
        tasks.remove(index);
        storage.saveTasks(tasks);
    }

    /**
     * Marks a specified task's isDone status to true
     * @param index Position of the task to be marked
     * @throws DukeException Thrown DukeException if there is no such task of that index
     */
    public void markTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index.");
        }
        tasks.get(index).mark();
        storage.saveTasks(tasks);
    }

    /**
     * Marks a specified task's isDone status to false
     * @param index Position of the task to be unmarked
     * @throws DukeException Thrown DukeException if there is no such task of that index
     */
    public void unmarkTaskAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index.");
        }
        tasks.get(index).unmark();
        storage.saveTasks(tasks);
    }

    /**
     * Returns a list with all the tasks in the TaskList
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.name.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    /**
     * Loads the saved tasks into the current list
     */
    private void loadTasks() {
        tasks = storage.loadTasks(); // Load tasks from storage when initializing
    }
}

