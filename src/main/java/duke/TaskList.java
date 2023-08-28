package duke;
import duke.Exceptions.InvalidTaskIndexException;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public Task getTask(int idx) throws InvalidTaskIndexException {
        if (idx < 0 || idx > taskList.size() - 1) {
            throw new InvalidTaskIndexException();
        }
        return taskList.get(idx);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask (int idx) throws InvalidTaskIndexException {
        if (idx < 0 || idx > taskList.size() - 1) {
            throw new InvalidTaskIndexException();
        }
        taskList.remove(idx);
    }

    public int size() {
        return taskList.size();
    }
}
