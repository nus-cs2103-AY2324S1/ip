import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task markAsUndone(int index) throws JukeError {
        if (index > tasks.size()) {
            throw new JukeError("That task does not exist!");
        }
        Task currTask = tasks.get(index - 1);
        currTask.markAsUndone();
        return currTask;
    }

    public Task markAsDone(int index) throws JukeError {
        if (index > tasks.size()) {
            throw new JukeError("That task does not exist!");
        }
        Task currTask = tasks.get(index - 1);
        currTask.markAsDone();
        return currTask;
    }

    public Task delete(int index) throws JukeError {
        if (index > tasks.size()) {
            throw new JukeError("That task does not exist!");
        }
        Task currTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        return currTask;
    }

}
