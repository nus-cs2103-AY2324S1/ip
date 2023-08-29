package duke.object;

import duke.object.task.Task;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void mark(int index) {
        this.get(index).mark();
    }

    public void unmark(int index) {
        this.get(index).unmark();
    }

    @Override
    public Task get(int index) {
        return super.get(index - 1);
    }

    @Override
    public Task remove(int index) {
        return super.remove(index - 1);
    }

}
