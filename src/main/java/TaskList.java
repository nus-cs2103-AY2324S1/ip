import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    protected int getTotalTasks() {
        return this.tasks.size();
    }

    protected Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    protected void addTask(Task newTask) {
        tasks.add(newTask);
    }

    protected void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    protected void markTaskDone(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
    }

    protected void markTaskUndone(int taskIndex) {
        tasks.get(taskIndex).markAsUndone();
    }
}
