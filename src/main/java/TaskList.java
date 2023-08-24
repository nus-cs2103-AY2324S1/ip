import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        this.taskList.add(newTask);
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

    public void markAsUndone(int index) {
        this.taskList.get(index).markAsUndone();
    }
}
