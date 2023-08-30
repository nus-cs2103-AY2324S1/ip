import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {

    ArrayList<Task> tasks;
    protected TaskList() {
        this.tasks = new ArrayList<>();
    }

    protected int getTotalTask() {
        return this.tasks.size();
    }

    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    protected Task deleteTask(int targetTask) {
        return this.tasks.remove(targetTask);
    }

    protected Task markComplete(int targetTask) {
        Task task = this.tasks.get(targetTask);
        task.markComplete();
        return task;
    }

     protected Task markIncomplete(int targetTask) {
        Task task = this.tasks.get(targetTask);
        task.markIncomplete();
        return task;
    }

    protected ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    protected void showAllTask() {
        int listNum = 1;
        for (Task task: tasks) {
            System.out.println(listNum + ". " + task);
            ++listNum;
        }
    }
}

