package duke.data;

import java.util.ArrayList;

import duke.data.task.Task;
import duke.data.exception.DukeException;

public class TaskList {
    private final ArrayList<Task> taskList;

    /** Constructor to initialize TaskList */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor to initialize TaskList.
     *
     * @param taskList Array of task created.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves the array of tasks created.
     *
     * @return arraylist of tasks created.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Adds task into arraylist of tasks.
     *
     * @param task The task to be added to array list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks task as done.
     *
     * @param task The task object to be marked as done.
     * @throws DukeException when the task is already marked.
     */
    public void markTask(Task task) throws DukeException {
        if (task.getDone()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but task is already marked!");
        }
        task.markAsDone();
    }

    /**
     * Marks task as undone.
     *
     * @param task The task object to be marked as undone.
     * @throws DukeException when the task is already unmarked.
     */
    public void unmarkTask(Task task) throws DukeException {
        if (!task.getDone()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but task is already marked!");
        }
        task.markAsNotDone();
    }

    /**
     * Deletes task from arraylist
     *
     * @param taskID The taskID of the task to be deleted.
     * @return the task that was deleted
     * @throws DukeException when the taskID is invalid.
     */
    public Task deleteTask(int taskID) throws DukeException {
        if (taskID <= 0 || taskID > taskList.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but task not found.");
        }
        return taskList.remove(taskID - 1);
    }

    /**
     * Checks whether the arraylist is empty.
     *
     * @return whether the arraylist is empty.
     */
    public boolean hasTasks() {
        return !taskList.isEmpty();
    }

    /**
     * Counts the number of tasks in arraylist.
     *
     * @return the number of tasks in arraylist.
     */
    public int countTasks() {
        return taskList.size();
    }

    /**
     * Retrieves the task based on taskID
     *
     * @param taskID the taskID of the task to be retrieved
     * @return task that is searched for.
     */
    public Task getTask(int taskID) {
        return taskList.get(taskID);
    }
}
