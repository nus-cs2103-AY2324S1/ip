package duke;

import duke.exceptions.InvalidTaskIDException;
import duke.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;

/**
 * contains the task list, and methods to modify the tasks in the list
 */
public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;

    //case where there are no existing tasks in storage
    public TaskList(ArrayList<Task> list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    public boolean isValidTaskID(int taskID) throws InvalidTaskIDException {
        if (taskID > this.list.size() - 1 || taskID < 0) {
            throw new InvalidTaskIDException();
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

    public void addTask(Task task, Storage storage) throws IOException {
        this.list.add(task);
        storage.updateFile(this.list);
        ui.showTaskAdded(task, this.getListSize());
    }

    //might remove returning of boolean!! see first
    public void deleteTask(int taskID, Storage storage) throws IOException, InvalidTaskIDException {
        if (isValidTaskID(taskID)) {
            Task toRemove = list.get(taskID);
            list.remove(taskID);
            storage.updateFile(this.list);
            ui.showDeleteTask(toRemove, this.getListSize());
        }
    }

    public void markTask(int taskID, Storage storage) throws IOException {
        this.list.get(taskID).mark(ui);
        storage.updateFile(this.list);
    }

    public void unmarkTask(int taskID, Storage storage) throws IOException {
        this.list.get(taskID).unmark(ui);
        storage.updateFile(this.list);
    }

    public int getListSize() {
        return this.list.size();
    }
}
