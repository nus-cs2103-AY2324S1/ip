package didier;

import didier.exception.TaskNumberException;
import didier.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int taskNumber) throws TaskNumberException {
        try {
            return tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberException(String.valueOf(taskNumber));
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) throws TaskNumberException {
        try {
            return tasks.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberException(String.valueOf(taskNumber));
        }
    }
}
