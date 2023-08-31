package duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    @Override
    public boolean add(Task task) {
        tasks.add(task);
        return false;
    }


    public Task get(int j) {
        return tasks.get(j);
    }

    public Task remove(int i) {
        tasks.remove(i);
        return null;
    }
}
