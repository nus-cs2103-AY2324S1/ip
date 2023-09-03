import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Ui ui;

    public TaskList(List<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        Task task = tasks.get(index);
        this.tasks.remove(index);
        ui.deleteTaskMessage(task, tasks.size());
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

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int numOfTasks() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuffer outputString = new StringBuffer();
        for(int i = 0; i < tasks.size(); i++) {
            outputString.append(i + 1);
            outputString.append(". ");
            outputString.append(tasks.get(i).toString());
            outputString.append("\n");
        }
        return outputString.toString();
    }
}

