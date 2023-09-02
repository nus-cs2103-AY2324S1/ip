import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void markTaskAsUnDone(int index) {
        this.tasks.get(index).markAsUnDone();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void displayTasks() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
}

