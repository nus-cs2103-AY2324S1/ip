import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task remove(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index");
        } else if (tasks.size() == 0) {
            throw new DukeException("There are no tasks to delete");
        }

        Task deletedTask = this.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    
}
