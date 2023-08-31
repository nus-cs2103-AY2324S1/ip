package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);

    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void printList() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

}
