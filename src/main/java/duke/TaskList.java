package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    private final TaskStorage storage;

    /**
     * Initialises duke.TaskList and restores existing tasks
     * from duke.TaskStorage provided as argument.
     * @param storage duke.TaskStorage to use for restore/save of tasks.
     * @throws DukeException If an issue occurs during restore of tasks.
     */
    public TaskList(TaskStorage storage) throws DukeException {
        this.storage = storage;
        this.tasks = storage.loadExistingTasks();
    }

    /**
     * Adds task.
     * @param task duke.Task to add.
     */
    public void addTask(Task task) throws DukeException {
        this.tasks.add(task);
        storeTasks();
    }

    /**
     * Deletes task by index.
     * @param index Index of task to delete.
     * @return duke.Task deleted.
     */
    public Task deleteTask(int index) throws DukeException {
        index -= 1;

        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("Index out of bounds");
        }

        Task task = this.tasks.remove(index );
        storeTasks();
        return task;
    }

    /**
     * Marks task as done.
     * @param index Index of task to mark.
     */
    public Task markTask(int index) throws DukeException {
        index -= 1;

        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("Index out of bounds");
        }

        Task task = this.tasks.get(index);
        task.markDone();
        storeTasks();
        return task;
    }

    /**
     * Marks task as not done.
     * @param index Index of task to unmark.
     */
    public Task unmarkTask(int index) throws DukeException {
        index -= 1;

        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("Index out of bounds");
        }

        Task task = this.tasks.get(index);
        task.unmarkDone();
        storeTasks();
        return task;
    }

    /**
     * Get all tasks.
     * @return All tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Find tasks with names that match search term.
     * @return List of tasks that match search.
     */
    public List<Task> findTasks(String search) {
        return this.tasks.stream()
                .filter(t -> t.getTaskName().contains(search))
                .collect(Collectors.toList());
    }

    private void storeTasks() throws DukeException {
        this.storage.storeTasks(this.tasks);
    }
}
