package rayshawn.chatbot.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.list = new ArrayList<>(taskList);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void removeTask(int index) {
        list.remove(index);
    }

    public int count() {
        return list.size();
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(list);
    }

}
