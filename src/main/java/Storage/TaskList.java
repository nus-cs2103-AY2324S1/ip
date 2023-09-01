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

    public boolean taskListEqual(TaskList other) {
        if (task.size() != other.task.size()) {
            return false;
        }

        for (int i = 0; i < task.size(); i++) {
            if (!task.get(i).equals(other.task.get(i))) {
                return false;
            }
        }

        return true;
    }

}
