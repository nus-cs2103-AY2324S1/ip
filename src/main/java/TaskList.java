import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return result;
    }
}