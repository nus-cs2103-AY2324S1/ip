package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int i) {
        list.remove(i);
    }

    public void mark(int i) {
        list.get(i).mark();
    }

    public void unmark(int i) {
        list.get(i).unmark();
    }

    public List<Task> list() {
        return list;
    }
}
