package functional;

import java.util.ArrayList;
import java.util.List;

public class TaskList<Task> extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(List<Task> list) {
        super(list);
    }

    public Task get(int index) {
        return super.get(index);
    }
}
