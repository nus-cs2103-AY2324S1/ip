package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.IndexOutOfBoundsException;
import duke.task.Task;

/**
 * A class that represents a list that stores tasks inputted by user
 */
public class TaskList {
    protected ArrayList<Task> taskArr;

    /**
     * A constructor for the TaskList object
     */
    public TaskList() {
        this.taskArr = new ArrayList<Task>();
    }

    /**
     * A constructor for the TaskList object
     * @param arr ArrayList of Task objects that TaskList object generated contains
     */
    public TaskList(ArrayList<Task> arr) {
        this.taskArr = arr;
    }

    /**
     * A method that returns task object at given index of TaskList
     * @param index index of Task object
     * @return specified Task object
     * @throws DukeException when index is out of range
     */
    public Task getTask(int index) throws DukeException {
        try {
            return this.taskArr.get(index);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * A method that returns length of TaskList
     * @return length of TaskList
     */
    public int length() {
        return this.taskArr.size();
    }

    /**
     * A method that marks specified Task object as done
     * @params index index of Task object
     * @throws DukeException when index is out of range
     */
    public void markTaskAsDone(int index) throws DukeException {
        this.getTask(index).markAsDone();
    }

    /**
     * A method that unmarks specified Task object as done
     * @param index index of Task object
     * @throws DukeException when index is out of range
     */
    public void markTaskAsNotDone(int index) throws DukeException {
        this.getTask(index).markAsNotDone();

    }

    /**
     * A method that returns the string representation of a Task object
     * at specified index
     * @param index task at given index of TaskList object
     * @return string representation of the Task object
     */
    public String taskToString(int index) {
        return this.taskArr.get(index).toString();
    }

    /**
     * A method that returns a string representation of the number of
     * tasks in a TaskList
     * @return string representation of the number of tasks in the TaskList
     */
    public String numTasksToString() {
        if (this.length() == 1) {
            return " task";
        }
        return " tasks";
    }

    /**
     * A method to to add a task to the TaskList
     * @param task task object to be added
     */
    public void addTask(Task task) {
        this.taskArr.add(task);
    }

    /**
     * A method to add a task to the TaskList
     * @param index index of Task object that user wants to delete
     */
    public void delete(int index) {
        this.taskArr.remove(index);
    }

    /**
     * A method to generate a string representing the TaskList in a format that can
     * be stored in the .txt file
     * String is passed to the Storage object to be processed
     * @return string representation of TaskList to be stored
     */
    public String storage() {
        String out = "";
        for (Task task : this.taskArr) {
            out += task.toBeStored();
        }
        return out;
    }

    /**
     * A method that takes in a string and returns tasklist containing
     * all tasks such that inputted string is a substring of the tasks' description
     * @param keyString string that user wants to compare tasks' descriptions against
     * @return TaskList object containing all desired tasks with descriptions being
     * superstring of inputted string
     */
    public TaskList filter(String keyString) {
        ArrayList<Task> out = new ArrayList<Task>();
        for (Task task : this.taskArr) {
            if (task.isKey(keyString)) {
                out.add(task);
            }
        }
        return new TaskList(out);
    }
}
