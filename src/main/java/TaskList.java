import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task mark(int i) {
        taskList.get(i).mark();
        return taskList.get(i);
    }

    public Task unmark(int i) {
        taskList.get(i).unmark();
        return taskList.get(i);
    }

    public Task remove(int i) {
        return taskList.remove(i);
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    public void iter(Consumer<? super Task> c) {
        taskList.forEach(c);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            result += (num + ". " + taskList.get(i) + "/n");
        }
        return result;
    }

}