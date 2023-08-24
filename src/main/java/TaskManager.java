import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public String listTasks() {
        if (tasks.size() == 0) {
            return "There are no tasks in your list.\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String item = (i + 1) + "." + tasks.get(i).toString() + "\n";
            sb.append(item);
        }
        return sb.toString();
    }

    public String addTask(Task task) {
        this.tasks.add(task);
        return "added: " + task;
    }
}
