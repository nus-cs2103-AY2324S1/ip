package duke.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exceptions.InvalidListFlagException;
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
     * @param list    list to be initialised.
     * @param storage storage to be used.
     * @param ui      ui to be used.
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
     */
    public boolean isValidTaskId(int taskId) {
        if (taskId > this.list.size() - 1 || taskId < 0) {
            return false;
        }
        return true;
    }

    /**
     * Appends all the current tasks into a string, and passes it to ui object for printing.
     *
     * @return the tasks the user currently has.
     */
    public String listTasks() {
        if (this.list.size() == 0) {
            return ui.showNumberOfTasks(0);
        } else {
            String result = "";
            for (int i = 0; i < this.list.size(); i++) {
                int index = i + 1;
                Task task = this.list.get(i);
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
     * Adds a task to the list, and updates storage.
     *
     * @param task id of task to be added.
     */
    public String addTask(Task task) {
        this.list.add(task);
        this.storage.updateFile(this.list);
        return ui.showTaskAdded(task, this.list.size());
    }

    /**
     * Deletes a task from the list if the input taskID is valid.
     * Updates storage.
     *
     * @param taskId if of task to delete.
     * @throws InvalidTaskIdException if taskId is invalid.
     */
    public String deleteTask(int taskId) throws InvalidTaskIdException {
        if (!isValidTaskId(taskId)) {
            throw new InvalidTaskIdException();
        }

        Task toRemove = this.list.get(taskId);
        this.list.remove(taskId);
        this.storage.updateFile(this.list);
        return ui.showDeleteTask(toRemove, this.list.size());
    }

    /**
     * Marks a task as completed, and updates storage.
     *
     * @param taskId id of task to be marked.
     * @return a success or failure message.
     * @throws InvalidTaskIdException if taskId is invalid.
     */
    public String markTask(int taskId) throws InvalidTaskIdException {
        if (!isValidTaskId(taskId)) {
            throw new InvalidTaskIdException();
        }

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
     * @param taskId id of task to be unmarked.
     * @return a success or failure message.
     * @throws InvalidTaskIdException if taskId is invalid.
     */
    public String unMarkTask(int taskId) throws InvalidTaskIdException {
        if (!isValidTaskId(taskId)) {
            throw new InvalidTaskIdException();
        }

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
     * @return list of tasks that contain keyword.
     */
    public String findMatches(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.list) {
            String desc = task.getTask();
            assert !desc.isEmpty() : "Description cannot be empty";
            if (desc.contains(keyword)) {
                matches.add(task);
            }
        }
        return ui.showMatches(listTasks(matches));
    }

    /**
     * Returns the tasks that are either due/ within the specified period.
     * Today refers to the start of today, at 00:00.
     *
     * @param when specified period.
     * @return tasks that are due/ within the specified period.
     * @throws InvalidListFlagException when list flag is of the incorrect format.
     */
    public String getUpcoming(String when) throws InvalidListFlagException {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();

        LocalDateTime endOfPeriod;

        switch (when) {
        case "today":
            endOfPeriod = start.plusDays(1);
            break;
        case "week":
            endOfPeriod = start.plusWeeks(1);
            break;
        case "fort":
            endOfPeriod = start.plusWeeks(2);
            break;
        default:
            throw new InvalidListFlagException();
        }

        ArrayList<Task> upcoming = new ArrayList<>();
        for (Task task : this.list) {
            if (task.isWithin(start, endOfPeriod)) {
                upcoming.add(task);
            }
        }
        return ui.showUpcoming(listTasks(upcoming));
    }
}
