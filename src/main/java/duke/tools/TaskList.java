package duke.tools;

import duke.tasks.Task;

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

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public String retrieveForStorage() {

        StringBuilder textForStorage = new StringBuilder();
        for (Task task : tasks) {
            textForStorage.append(task.formatForStorage()).append("\n");
        }
        return textForStorage.toString();
    }

}
