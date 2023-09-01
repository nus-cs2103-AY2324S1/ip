package duke.list;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

public class FunnyList {

    private ArrayList<Task> taskList;

    public FunnyList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public FunnyList() {

        this.taskList = new ArrayList<>();
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public Task completeTask(int index) throws DukeException {
        if (index < 1 || index > this.taskList.size()) {
            throw new DukeException("Index does not exist");
        }
        return this.taskList.get(index - 1).completeTask();
    }

    public Task undoTask(int index) throws DukeException {
        if (index < 1 || index > this.taskList.size()) {
            throw new DukeException("Index does not exist");
        }
        return this.taskList.get(index - 1).undoTask();
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 1 || index > this.taskList.size()) {
            throw new DukeException("Index does not exist");
        }
        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        return task;
    }
    public void add(Task task) {
        this.taskList.add(task);
    }

}
