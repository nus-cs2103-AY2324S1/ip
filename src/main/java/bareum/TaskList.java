package bareum;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void unmarkAsDone(int index) {
        this.taskList.get(index).unmarkAsDone();
    }

    public void tag(int index, String tag) {
        this.taskList.get(index).tag(tag);
    }

    public void delete(int index) {
        this.taskList.remove(index);
    }
}
