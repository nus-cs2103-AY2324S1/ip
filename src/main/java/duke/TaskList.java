package duke;


import java.util.ArrayList;

import exception.DukeException;
import task.Task;

/**
 * Class that handle lists of tasks saved in ArrayList.
 *
 * @author syamfarh
 */
public class TaskList {

    /** store tasks in an ArrayList */
    private ArrayList<Task> tasks;

    /** Initialize Tasklist with empty ArrayList */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialize Tasklist with current list of tasks.
     *
     * @param tasks list of tasks to be referenced to Tasklist
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Return ArrayList tasks.
     *
     * @return ArrayList tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Return task from the zeroBasedIndex i.
     *
     * @param i zeroBasedIndex
     *
     * @return task
     */
    public Task getTasks(int i) {
        assert i >= 0 && i < this.getSize();
        return this.tasks.get(i);
    }

    /**
     * Add new task into the Tasklist
     *
     * @param task new Task
     */
    public void addTask(Task task) {
        assert task != null;
        this.tasks.add(task);
    }

    /**
     * Remove task from TaskList based on zeroBasedIndex i.
     * If index is invalid, function will throw DukeException with custom message.
     *
     * @param i zeroBasedIndex
     * @return task on index i
     * @throws DukeException if index is invalid
     */
    public Task removeTask(int i) throws DukeException {
        assert i >= 0 && i < this.getSize();
        if (i < this.tasks.size()) {
            return this.tasks.remove(i);
        } else {
            throw new DukeException("☹ OOPS!!! The number input does not exist.", new IndexOutOfBoundsException());
        }
    }

    /**
     * Return the number of tasks in the TaskList.
     *
     * @return number of tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * mark Task at zeroBasedIndex i to be done.
     *
     * @param i zeroBasedIndex
     * @throws DukeException if i is invalid
     */
    public void markTaskDone(int i) throws DukeException {
        assert i >= 0 && i < this.getSize();
        if (i < this.tasks.size()) {
            this.tasks.get(i).markAsDone();
        } else {
            throw new DukeException("☹ OOPS!!! The number input does not exist.", new IndexOutOfBoundsException());
        }
    }

    /**
     * unmark Task at zeroBasedIndex i.
     *
     * @param i zeroBasedIndex
     * @throws DukeException if i is invalid
     */
    public void markTaskUndone(int i) throws DukeException {
        assert i >= 0 && i < this.getSize();
        if (i < this.tasks.size()) {
            this.tasks.get(i).markAsUndone();
        } else {
            throw new DukeException("☹ OOPS!!! The number input does not exist.", new IndexOutOfBoundsException());
        }
    }

    public TaskList findTasks(String desc) {
        assert !desc.isBlank();
        TaskList returnList = new TaskList();
        for (Task i : this.tasks) {
            if (i.getDescription().contains(desc)) {
                returnList.addTask(i);
            }
        }
        return returnList;
    }
}
