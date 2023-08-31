import java.util.ArrayList;



public class TaskList {

    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }

    public void markTask(int index) {
        this.tasks.get(index).markStatus();
    }

    public void unmarkTask(int index) {
        this.tasks.get(index).unmarkStatus();
    }

}
