package joe;

import joe.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public void remove(int idx) {
        tasks.remove(idx);
    }

    public int size() {
        return tasks.size();
    }

    public List<String> getStringList() {
        return tasks.stream().map(Task::toString).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1);
            sb.append(".");
            sb.append(tasks.get(i).toString());
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
