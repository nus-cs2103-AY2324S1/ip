package Storage;
import TaskManager.Tasks;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Tasks> task;

    public TaskList(ArrayList<Tasks> task) {
        this.task = task;
    }

    public void add(Tasks t) {
        task.add(t);
    }

    public int size() {
        return task.size();
    }

    public boolean isEmpty() {
        return task.isEmpty();
    }

    public void remove(int i) {task.remove(i);}

    public ArrayList<Tasks> getTasks() {
        return task;
    }

    public Tasks get(int index) {
        return task.get(index);
    }

}
