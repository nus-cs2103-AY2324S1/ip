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

    public TaskList(ArrayList<Task> list, Storage storage, Ui ui) {
        this.list = list;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns true if the input taskID is in the range [0, list.size() -1].
     *
     * @param taskID id to be checked
     * @return true if the taskID is valid
     * @throws InvalidTaskIDException if taskID is out of range
     */
    public boolean isValidTaskID(int taskID) throws InvalidTaskIDException {
        if (taskID > this.list.size() - 1 || taskID < 0) {
            throw new InvalidTaskIDException();
        }
        return true;
    }

    /**
     * Appends all the tasks into a string, and passes it to ui object
     * for printing. If no tasks, calls ui.showNoTasks().
     */
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

    /**
     * Adds a task to the list. Calls storage.updateFile() to update the
     * data file.
     *
     * @param task id of task to be added
     */
    public void addTask(Task task) {
        this.list.add(task);
        this.storage.updateFile(this.list);
        ui.showTaskAdded(task, this.getListSize());
    }

    /**
     * Deletes a task from the list if the input taskID is valid.
     * Calls storage.updateFile() to update the data file.
     *
     * @param taskID if of task to delete
     * @throws InvalidTaskIDException if taskID is invalid
     */
    public void deleteTask(int taskID) throws InvalidTaskIDException {
        if (isValidTaskID(taskID)) {
            Task toRemove = list.get(taskID);
            list.remove(taskID);
            this.storage.updateFile(this.list);
            ui.showDeleteTask(toRemove, this.getListSize());
        }
    }

    /**
     * Marks a task as completed, and updates storage.
     *
     * @param taskID id of task to mark
     */
    public void markTask(int taskID) {
        Task task = this.list.get(taskID);
        if (task.canMark()) {
            ui.showMarkTask(false, task);
            this.storage.updateFile(this.list);
        } else {
            ui.showMarkTask(true, task);
        }
    }

    /**
     * Marks a task as uncompleted, and updates storage.
     *
     * @param taskID id of task to unmark
     */
    public void unmarkTask(int taskID) {
        Task task = this.list.get(taskID);
        if (task.canUnMark()) {
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

    /**
     * Returns the size of the list.
     *
     * @return size of list
     */
    public int getListSize() {
        return this.list.size();
    }
}
