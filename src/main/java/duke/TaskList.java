package duke;

import duke.exceptions.InvalidTaskIDException;
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

    public String listTasks(ArrayList<Task> tasks) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            Task task = tasks.get(i);
            result += index + ". " + task.toString() + "\n";
        }
        return result;
    }

    public void addTask(Task task) {
        this.list.add(task);
        this.storage.updateFile(this.list);
        ui.showTaskAdded(task, this.getListSize());
    }

    //might remove returning of boolean!! see first
    public void deleteTask(int taskID) throws InvalidTaskIDException {
        if (isValidTaskID(taskID)) {
            Task toRemove = list.get(taskID);
            list.remove(taskID);
            this.storage.updateFile(this.list);
            ui.showDeleteTask(toRemove, this.getListSize());
        }
    }

    public void markTask(int taskID) {
        Task task = this.list.get(taskID);
        if (task.mark()) {
            ui.showMarkTask(false, task);
            this.storage.updateFile(this.list);
        } else {
            ui.showMarkTask(true, task);
        }
    }

    public void unmarkTask(int taskID) {
        Task task = this.list.get(taskID);
        if (task.unmark()) {
            ui.showUnmarkTask(true, task);
            this.storage.updateFile(this.list);
        } else {
            ui.showUnmarkTask(false, task);
        }
    }

    public ArrayList<Task> findMatches(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : list) {
            String desc = task.getTask();
            if (desc.contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    public int getListSize() {
        return this.list.size();
    }
}
