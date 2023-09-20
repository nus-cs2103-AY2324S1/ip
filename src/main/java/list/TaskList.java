package list;

import java.util.ArrayList;

import dukeexception.DukeException;
import tasks.Task;



/**
 * The class that stores Tasks inside an Arraylist.
 */
public class TaskList {

    private final ArrayList<Task> taskList;
    /**
     * Constructs method that will create a new TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList(TaskList userList) {
        this.taskList = convert(userList);
    }

    /**
     * Converts a TaskList to an ArrayList.
     *
     * @param userList The TaskList that is going to be converted.
     * @return An Arraylist that is converted from TaskList.
     */
    public ArrayList<Task> convert(TaskList userList) {
        for (int i = 0; i < userList.size(); i++) {
            taskList.add(i, userList.get(i));
        }
        return taskList;
    }
    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return taskList.size(); }

    /**
     * Gets the task in the list.
     *
     * @param i The index of the task needed.
     * @return The task that is called for.
     */

    public Task get(int i) {
        return taskList.get(i); }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task that the user wants to add.
     */
    public void add(Task task) {
        taskList.add(task); }

    /**
     * Deletes a task depending on the index provided.
     *
     * @param i The index of the task.
     * @return The task that has been removed.
     * @throws DukeException If the index is out of bound.
     */
    public Task deleteTask(int i) throws DukeException {
        if (i > this.taskList.size() || i < 1) {
            throw new DukeException("Invalid Index provided.");
        }
        Task removing = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        return removing;
    }

    /**
     * Marks a task as done.
     *
     * @param i The index of the task.
     * @return The task that is marked.
     * @throws DukeException If the index is out of bound.
     */
    public Task markTask(int i) throws DukeException {
        if (i > this.taskList.size() || i < 1) {
            throw new DukeException("Invalid Index provided.");
        }
        Task marking = this.taskList.get(i - 1);
        marking.markDone();
        return marking;
    }

    /**
     * Marking the task as unmarked.
     *
     * @param i The index of the task.
     * @return The task that is unmarked.
     * @throws DukeException If the index is out of bound.
     */
    public Task unmarkTask(int i) throws DukeException {
        if (i > this.taskList.size() || i < 1) {
            throw new DukeException("Invalid Index provided.");
        }
        Task unMarking = this.taskList.get(i - 1);
        unMarking.unmarkDone();
        return unMarking;
    }

    /**
     * Clears the TaskList
     *
     * @return An empty list
     */
    public TaskList clear() {
        this.taskList.clear();
        return new TaskList();
    }
}
