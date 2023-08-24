import java.util.ArrayList;
import java.util.List;

/**
 * CS2103T IP
 * AY 23/24 Semester 1
 *
 * <p> A Task Manager that helps manage the list of tasks </p>
 *
 * @author Koo Yu Cong
 * @version CS2103T AY 23/24 Sem 1
 */
public class TaskManager {
    private List<Task> tasks;

    /**
     * A constructor that constructs a Task Manager
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Lists the tasks in order
     *
     * @return The string representation of the list of tasks.
     */
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

    /**
     * Adds a task to the end of the list of tasks.
     *
     * @param task The task to be added.
     * @return The string description after adding the task.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "added: " + task;
    }

    /**
     * Marks the i-th task as either done or not done
     *
     * @param i The index of the task in the list(1-indexed)
     * @param done Whether to mark the task as done or not
     * @return The String description after marking the task
     * @throws DukeException if the index is out of bound
     */
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
