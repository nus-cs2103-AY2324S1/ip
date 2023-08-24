import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> taskLists;

    public TaskList() {
        taskLists = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskLists.add(task);
    }

    public Task getTask(int index) {
        return taskLists.get(index);
    }

    public void mark(int index) {
        taskLists.get(index).markDone();
    }

    public void unmark(int index) {
        taskLists.get(index).markUndone();
    }

    public int getLength() {
        return taskLists.size();
    }

    public void remove(int index) {
        taskLists.remove(index);
    }
}
