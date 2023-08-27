import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    public void mark(int index) {
        this.tasks.get(index).completeTask();
    }

    public void unmark(int index) {
        this.tasks.get(index).revertTask();
    }

    public void list() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ") " + this.tasks.get(i).toString());
        }
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

}
