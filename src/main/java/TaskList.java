import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedFile) {
        this.tasklist = loadedFile;
    }

    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    public Task deleteTask(int index) {
        Task deletedTask = tasklist.get(index);
        tasklist.remove(index);
        return deletedTask;
    }

    public Task getTask(int index) {
        return tasklist.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasklist;
    }

    public int getSize() {
        return tasklist.size();
    }

    public void print() {
        System.out.print("Here are the tasks in your list: \n");
        for (int i = 0; i < tasklist.size(); i++) {
            int index = i + 1;
            Task t = tasklist.get(i);
            System.out.println(index + "." + t.toString());
        }
    }
}
