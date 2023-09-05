package duke;

import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * contains the task list, and methods to modify the tasks in the list
 */
public class TaskList {
    private ArrayList<Task> list;

    private Storage storage;
    private Ui ui;

    //case where there are no existing tasks in storage
    public TaskList(ArrayList<Task> list, Storage storage, Ui ui) {
        this.list = list;
        this.storage = storage;
        this.ui = ui;
    }

    public boolean isValidTaskId(int taskId) throws InvalidTaskIdException {
        if (taskId > this.list.size() - 1 || taskId < 0) {
            throw new InvalidTaskIdException();
        }
        return true;
    }

    public void listTasks() {
        if (list.size() == 0) {
            ui.showNoTasks();
        } else {
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                Task task = list.get(i);
                result += index + ". " + task.toString() + "\n";
            }
            ui.showTasks(result);
        }
    }

    public void addTask(Task task) {
        this.list.add(task);
        this.storage.updateFile(this.list);
        ui.showTaskAdded(task, this.getListSize());
    }

    //might remove returning of boolean!! see first
    public void deleteTask(int taskId) throws InvalidTaskIdException {
        if (isValidTaskId(taskId)) {
            Task toRemove = list.get(taskId);
            list.remove(taskId);
            this.storage.updateFile(this.list);
            ui.showDeleteTask(toRemove, this.getListSize());
        }
    }

    public void markTask(int taskId) {
        Task task = this.list.get(taskId);
        if (task.canMark()) {
            ui.showMarkTask(false, task);
            this.storage.updateFile(this.list);
        } else {
            ui.showMarkTask(true, task);
        }
    }

    public void unMarkTask(int taskId) {
        Task task = this.list.get(taskId);
        if (task.canUnMark()) {
            ui.showUnMarkTask(true, task);
            this.storage.updateFile(this.list);
        } else {
            ui.showUnMarkTask(false, task);
        }
    }

    public int getListSize() {
        return this.list.size();
    }
}
