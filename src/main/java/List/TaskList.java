package List;

import Tasks.Task;
import DukeException.DukeException;

import java.util.ArrayList;

/**
 * A class that stores Tasks inside an Arraylist.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList(TaskList userList) {
        this.taskList = convert(userList);
    }

    /**
     * A method that will convert a TaskList to an ArrayList.
     *
     * @param userList the TaskList that is going to be converted.
     * @return an Arraylist that is converted from Tasklist.
     */
    public ArrayList<Task> convert(TaskList userList) {
        for (int i = 0; i < userList.size(); i++) {
            taskList.add(i, userList.get(i));
        }
        return taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * A method that returns the size of the TaskList.
     *
     * @return the size of the TaskList.
     */
    public int size() { return taskList.size(); }

    /**
     * A method that will get the task in the list.
     *
     * @param i the index of the task needed.
     * @return the task that is called for.
     */

    public Task get(int i) { return taskList.get(i); }

    /**
     * A method that adds a task to the TaskList.
     *
     * @param task the task that the user wants to add.
     */
    public void add(Task task) { taskList.add(task); }

    /**
     * A method that will delete a task depending on the index provided.
     *
     * @param i the index of the task.
     * @return the task that has been removed.
     * @throws DukeException if the index is out of bound.
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
     * A method that will mark a task as done.
     *
     * @param i the index of the task.
     * @return the task that is marked.
     * @throws DukeException if the index is out of bound.
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
     * A method that will unmark a task as done.
     *
     * @param i the index of the task.
     * @return the task that is unmarked.
     * @throws DukeException if the index is out of bound.
     */
    public Task unmarkTask(int i) throws DukeException {
        if (i > this.taskList.size() || i < 1) {
            throw new DukeException("Invalid Index provided.");
        }
        Task unmarking = this.taskList.get(i - 1);
        unmarking.unmarkDone();
        return unmarking;
    }

    /**
     * A method that clears the TaskList
     *
     * @return an empty list
     */
    public TaskList clear() {
        this.taskList.clear();
        return new TaskList();
    }
}
