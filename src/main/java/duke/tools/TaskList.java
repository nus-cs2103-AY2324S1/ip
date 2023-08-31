package duke.tools;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public String retrieveForStorage() {

        StringBuilder textForStorage = new StringBuilder();
        for (Task task : tasks) {
            textForStorage.append(task.formatForStorage()).append("\n");
        }
        return textForStorage.toString();
    }

}
