package duke.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import duke.exception.DukeException;

/**
 * Represents a list of tasks in the Duke application.
 * This class extends ArrayList and provides additional methods for managing tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructs a TaskList with the specified collection of tasks.
     *
     * @param load The collection of tasks to load into the task list.
     */
    public TaskList(Collection<Task> load) {
        super(load);
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Adds a Todo task with the specified name to the task list.
     *
     * @param name The name or description of the task.
     * @return The added Todo task.
     */
    public Task addTask(String name) {
        Task t = Task.of(name);
        add(t);
        return t;
    }

    /**
     * Adds a Deadline task with the specified name and deadline to the task list.
     *
     * @param name The name or description of the task.
     * @param d The deadline of the task.
     * @return The added Deadline task.
     */
    public Task addTask(String name, LocalDate d) {
        Task t = Task.of(name, d);
        add(t);
        return t;
    }

    /**
     * Adds an Event task with the specified name and event times to the task list.
     *
     * @param name The name or description of the task.
     * @param dFrom The start time of the event.
     * @param dTo The end time of the event.
     * @return The added Event task.
     */
    public Task addTask(String name, LocalDate dFrom, LocalDate dTo) {
        Task t = Task.of(name, dFrom, dTo);
        add(t);
        return t;
    }

    /**
     * Marks the task at the specified index as done and returns it.
     *
     * @param index The index of the task to mark.
     * @return The marked task.
     * @throws DukeException If there is an error while marking the task.
     */
    public Task mark(int index) throws DukeException {
        try {
            Task t = get(index - 1);
            if (t.mark()) {
                return t;
            } else {
                throw new DukeException("OOPS!!! This task has already be marked!\n");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! There is no such a task\n");
        }
    }

    /**
     * Unmarks the task at the specified index (marks it as not done) and returns it.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked task.
     * @throws DukeException If there is an error while unmarking the task.
     */
    public Task unmark(int index) throws DukeException {
        try {
            Task t = get(index - 1);
            if (t.unmark()) {
                return t;
            } else {
                throw new DukeException("OOPS!!! This task hasn't be marked yet!\n");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! There is no such a task\n");
        }
    }

    /**
     * Searches the task list for tasks that match the given search query.
     *
     * @param s The search query to match against task names.
     * @return An ArrayList containing tasks that match the search query.
     */
    public ArrayList<Task> find(String s) {
        if (s.equals("") || s.equals(" ")) {
            return new ArrayList<>();
        } else {
            ArrayList<Task> res = new ArrayList<>();
            forEach(x -> {
                if (x.isMatch(s.substring(1))) {
                    res.add(x);
                }
            });
            return res;
        }
    }

    /**
     * Deletes the task at the specified index from the task list and returns it.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws DukeException If there is an error while deleting the task.
     */
    public Task delete(int index) throws DukeException {
        try {
            Task t = get(index - 1);
            remove(index - 1);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! There is no such a task\n");
        }
    }
}
