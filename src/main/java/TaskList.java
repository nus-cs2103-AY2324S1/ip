import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) throws DukeException {
        if (tasks == null) {
            throw new DukeException("Empty taskList");
        } else {
            this.tasks = tasks;
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    };

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        Task removedTask = tasks.remove(index);
        return removedTask;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
