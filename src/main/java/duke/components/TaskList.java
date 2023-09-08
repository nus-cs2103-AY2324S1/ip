package duke.components;

import java.util.ArrayList;

import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Task;

/**
 * Contains the task list, and methods to modify the tasks in the list
 */
public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;
    private Ui ui;

    /**
     * Class constructor for TaskList.
     *
     * @param list list to be initialised.
     * @param storage storage to be used.
     * @param ui ui to be used.
     */
    public TaskList(ArrayList<Task> list, Storage storage, Ui ui) {
        this.list = list;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns true if the input taskID is in the range [0, list.size() -1].
     *
     * @param taskId id to be checked.
     * @return true if the taskID is valid.
     * @throws InvalidTaskIdException if taskID is out of range.
     */
    public boolean isValidTaskId(int taskId) throws InvalidTaskIdException {
        if (taskId > this.list.size() - 1 || taskId < 0) {
            throw new InvalidTaskIdException();
        }
        return true;
    }

    /**
     * Appends all the tasks into a string, and passes it to ui object for printing.
     * If no tasks, calls ui.showNoTasks().
     */
    public String listTasks() {
        if (list.size() == 0) {
            return ui.showNoTasks();
        } else {
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                Task task = list.get(i);
                result += index + ". " + task.toString() + "\n";
            }
            return ui.showTasks(result);
        }
    }

    /**
     * Appends all tasks in the given list into a single string.
     *
     * @param tasks list of Tasks to be printed.
     * @return a String of tasks.
     */
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
     * Adds a task to the list. Calls storage.updateFile() to update the data file.
     *
     * @param task id of task to be added.
     */
    public String addTask(Task task) {
        this.list.add(task);
        this.storage.updateFile(this.list);
        return ui.showTaskAdded(task, this.getListSize());
    }

    /**
     * Deletes a task from the list if the input taskID is valid.
     * Calls storage.updateFile() to update the data file.
     *
     * @param taskId if of task to delete.
     * @throws InvalidTaskIdException if taskID is invalid.
     */
    public String deleteTask(int taskId) throws InvalidTaskIdException {
        if (isValidTaskId(taskId)) {
            Task toRemove = list.get(taskId);
            list.remove(taskId);
            this.storage.updateFile(this.list);
            return ui.showDeleteTask(toRemove, this.getListSize());
        }
        return "";
    }

    /**
     * Marks a task as completed, and updates storage.
     *
     * @param taskId id of task to mark.
     */
    public String markTask(int taskId) {
        Task task = this.list.get(taskId);
        if (task.canMark()) {
            this.storage.updateFile(this.list);
            return ui.showMarkTask(false, task);
        } else {
            return ui.showMarkTask(true, task);
        }
    }

    /**
     * Marks a task as uncompleted, and updates storage.
     *
     * @param taskId id of task to unmark.
     */
    public String unMarkTask(int taskId) {
        Task task = this.list.get(taskId);
        if (task.canUnMark()) {
            this.storage.updateFile(this.list);
            return ui.showUnMarkTask(true, task);
        } else {
            return ui.showUnMarkTask(false, task);
        }
    }

    /**
     * Returns a list of tasks that contains the keyword.
     *
     * @param keyword specified keyword to be searched for.
     * @return list of tasks.
     */
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
     * @return size of list.
     */
    public int getListSize() {
        return this.list.size();
    }
}
