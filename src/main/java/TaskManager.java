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

    public String markTask(int i, boolean done) throws DukeException {
        i--;
        if (i < 0 || i >= this.tasks.size()) {
            // invalid index
            throw new DukeException("Please input a valid index for the task to marked/unmarked");
        }

        Task task = this.tasks.get(i);
        task.markTask(done);

        return done
                ? "Nice! I've marked this task as done:\n  " + task.toString() + "\n"
                : "OK, I've marked this task as not done yet:\n  " + task.toString() + "\n";
    }
}
