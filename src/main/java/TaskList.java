import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void changeStatusOfTask(int index) {
        this.tasks.get(index).changeStatus();
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

}
