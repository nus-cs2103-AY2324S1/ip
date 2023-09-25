import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> taskLists;

    public TaskList() {
        taskLists = new ArrayList<>();
    }

    public TaskList(List<Task> arr) {
        taskLists = arr;
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

    public String writeTaskList() {
        String res = "";
        String temp;
        for (int i = 0; i < taskLists.size(); i++) {
            temp = taskLists.get(i).storedString();
            res = res + temp + "\n";
        }
        return res;
    }
}
