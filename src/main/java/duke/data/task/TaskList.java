package duke.data.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.data.exception.DukeException;

/**
 * Represents a collection of tasks within the chatbot applicaiton.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(Task... tasks) {
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns and removes the task specified from the collection of tasks.
     *
     * @param taskNumber The index of the task in the list, starting from 1.
     * @return The task specified.
     * @throws DukeException If no such task exists.
     */
    public Task remove(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        return tasks.remove(taskNumber - 1);
    }

    /**
     * Returns and marks the task specified as done.
     *
     * @param taskNumber The index of the task in the list, starting from 1.
     * @return The task specified.
     * @throws DukeException If no such task exists.
     */
    public Task mark(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Returns and marks the task specified as not done yet.
     *
     * @param taskNumber The index of the task in the list, starting from 1.
     * @return The task specified.
     * @throws DukeException If no such task exists.
     */
    public Task unmark(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();
        return task;
    }

    public void clear() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public String getStorageString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.getStorageString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
