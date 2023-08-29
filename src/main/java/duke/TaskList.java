package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public TaskList(List<Task> listOfTasks) {
        this.lst = listOfTasks;
    }

    public Task get(int i) {
        return lst.get(i);
    }
    public void remove(int i) {
        lst.remove(i);
    }

    public int size() {
        return this.lst.size();
    }

    public void add(Task t) {
        this.lst.add(t);
    }

}
