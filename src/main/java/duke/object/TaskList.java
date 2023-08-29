package duke.object;

import duke.exception.OutOfBoundsException;
import duke.object.task.Task;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void mark(int index) throws OutOfBoundsException {
        this.access(index).mark();
    }

    public void unmark(int index) throws OutOfBoundsException {
        this.access(index).unmark();
    }

    public Task access(int index) throws OutOfBoundsException {
        this.checkBounds(index);
        return super.get(index - 1);
    }

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
