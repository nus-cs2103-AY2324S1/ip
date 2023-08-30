package duke.task;

import duke.exception.DukeException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskArr;
    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    public int size() {
        return taskArr.size();
    }

    public Task get(int index) {
        return this.taskArr.get(index);
    }

    public void addTask(Task task) {
        this.taskArr.add(task);
    }

    public void deleteTask(int index) {
        this.taskArr.remove(index);
    }

    public void markTask(int index) {
        taskArr.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws DukeException {
        taskArr.get(index).unmark();
    }
}
