package duke.task;

import java.util.ArrayList;

import duke.DukeException;

/**
 * Represents a list of Tasks inherits from ArrayList of Task to contain user tasks.
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Marks task of specified index as done.
     * @param index Specified index
     * @return Task marked as done
     * @throws DukeException if invalid task index
     */
    public Task markDone(int index) throws DukeException {
        if (index < 0 || index >= super.size()) {
            throw new DukeException("Invalid task index");
        }
        super.get(index).markDone();
        return super.get(index);
    }

    /**
     * Marks task of specified index as undone.
     * @param index Specified index
     * @return Task marked as undone
     * @throws DukeException if invalid task index
     */
    public Task unmarkDone(int index) throws DukeException {
        if (index < 0 || index >= super.size()) {
            throw new DukeException("Invalid task index");
        }
        super.get(index).unmarkDone();
        return super.get(index);
    }
}
