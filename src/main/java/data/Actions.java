package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import tasks.Task;

/**
 * Represents a collection of tasks and provides various utility methods
 * for task management and interaction.
 */
public class Actions {
    private final ArrayList<Task> actions = new ArrayList<>();

    /**
     * Adds a single task to the list.
     *
     * @param input The task to be added.
     */
    public void add(Task input) {
        actions.add(input);
    }

    /**
     * Adds a list of tasks to the collection.
     *
     * @param tasks List of tasks to be added.
     */
    public void add(List<Task> tasks) {
        actions.addAll(tasks);
    }

    /**
     * @return Returns the list of tasks.
     */
    public ArrayList<Task> list() {
        return actions;
    }

    /**
     * @return Returns the string representation of the list of tasks.
     */
    public String stringList() {
        return stringList(this.actions);
    }

    /**
     * Converts a list of tasks into a string representation.
     *
     * @param actions List of tasks to be formatted.
     * @return String representation of the list of tasks.
     */
    public String stringList(ArrayList<Task> actions) {
        List<String> formattedTasks = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            String formattedIndex = String.format("%2d", i + 1);
            formattedTasks.add(formattedIndex + ". " + actions.get(i));
        }
        return String.join("\n", formattedTasks);
    }

    /**
     * Retrieves a task at a specific index.
     *
     * @param idx Index of the task.
     * @return Task at the specified index.
     * @throws DukeException If the index is out of bounds.
     */
    public Task getAction(int idx) throws DukeException {
        if (idx < actions.size() && idx > -1) {
            return actions.get(idx);
        }
        throw new DukeException(" Task number invalid; ensure task number is > 0.");
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param idx Index of the task to be marked.
     * @return The task that has been marked as done.
     * @throws DukeException If the index is out of bounds.
     */
    public Task mark(int idx) throws DukeException {
        Task toMark = getAction(idx);
        toMark.markAsDone();
        return toMark;
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param idx Index of the task to be unmarked.
     * @return The task that has been unmarked.
     * @throws DukeException If the index is out of bounds.
     */
    public Task unmark(int idx) throws DukeException {
        Task toUnmark = getAction(idx);
        toUnmark.unMark();
        return toUnmark;
    }

    /**
     * @return The total number of tasks in the list.
     */
    public int size() {
        return actions.size();
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param idx Index of the task to be deleted.
     * @return The task that has been deleted.
     * @throws DukeException If the index is out of bounds.
     */
    public Task delete(int idx) throws DukeException {
        getAction(idx);
        return actions.remove(idx);
    }

    /**
     * Retrieves all tasks scheduled on a specific date.
     *
     * @param date The date to filter tasks by.
     * @return List of tasks scheduled on the specified date.
     */
    public List<Task> tasksOnDate(LocalDateTime date) {
        return actions.stream()
                .filter(task -> task.isOnDate(date))
                .collect(Collectors.toList());
    }
}
