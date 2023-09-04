package duke.object;

import java.util.ArrayList;

import duke.exception.OutOfBoundsException;
import duke.object.task.Task;

/**
 * List containing Tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Marks task at an index.
     *
     * @param index The index at which to mark the task.
     * @throws OutOfBoundsException When the index is not in the list.
     */
    public void mark(int index) throws OutOfBoundsException {
        this.access(index).mark();
    }

    /**
     * Unmarks task at an index.
     *
     * @param index The index at which to unmark the task.
     * @throws OutOfBoundsException When the index is not in the list.
     */
    public void unmark(int index) throws OutOfBoundsException {
        this.access(index).unmark();
    }

    /**
     * Accesses task at an index.
     *
     * @param index The index at which to access the task.
     * @return The task at the index.
     * @throws OutOfBoundsException When the index is not in the list.
     */
    public Task access(int index) throws OutOfBoundsException {
        this.checkBounds(index);
        return super.get(index - 1);
    }

    /**
     * Deletes task at an index.
     *
     * @param index The index at which to delete the task.
     * @return The deleted task.
     * @throws OutOfBoundsException When the index is not in the list.
     */
    public Task delete(int index) throws OutOfBoundsException {
        this.checkBounds(index);
        return super.remove(index - 1);
    }

    private void checkBounds(int index) throws OutOfBoundsException {
        if (index > this.size()) {
            throw new OutOfBoundsException(index, this.size());
        }
    }

}
