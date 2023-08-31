package duke.data;

import java.util.ArrayList;
import duke.data.task.Task;
import duke.data.exception.DukeException;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
    public void markTask(Task task) throws DukeException {
        if (task.getDone())
            throw new DukeException("☹ OOPS!!! I'm sorry, but task is already marked!");
        task.markAsDone();
    }

    public void unmarkTask(Task task) throws DukeException {
        if (!task.getDone())
            throw new DukeException("☹ OOPS!!! I'm sorry, but task is already marked!");
        task.markAsNotDone();
    }

    public Task deleteTask(int taskID) throws DukeException {
        if (taskID <= 0 || taskID > taskList.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but task not found.");
        }
        return taskList.remove(taskID - 1);
    }

    public boolean hasTasks() {
        return !taskList.isEmpty();
    }

    public int countTasks() {
        return taskList.size();
    }

    public Task getTask(int taskID) {
        return taskList.get(taskID);
    }
}
