package Duke;

import java.util.ArrayList;
import java.util.List;


public class TaskList {


    private List<Task> taskList = new ArrayList<>();

    public int size() {
        return this.taskList.size();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        if (index <= this.taskList.size() && index >= 1) {
            return this.taskList.get(index - 1);
        } else {
            return null;
        }
    }
}

