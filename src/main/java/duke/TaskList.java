package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    /**
     * A task list that stores the user's tasks.
     */
    private ArrayList<Task> taskList;
    /**
     * An index that tracks the current newest position in the task list.
     */
    private int index;

    /**
     * Constructor for the duke.TaskList class.
     *
     * @param list The list to become the list in the task list.
     */
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
        this.index = list.size();
    }

    /**
     * Constructor for the duke.TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
        this.index = 1;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added into the task list.
     */
    public void addTask(Task task) throws DukeException {
        this.taskList.add(task);
        this.index++;
    }

    /**
     * Marks the task with the specified index as done.
     *
     * @param index The index of the task to be marked as done
     */
    public void markDone(int index) throws DukeException {
        try {
            Task task = this.taskList.get(index - 1);
            task.setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no task with this index number.");
        }
    }

    /**
     * Marks the task with the specified index as not done.
     *
     * @param index The index of the task to be marked as not done
     */
    public void markNotDone(int index) throws DukeException {
        try {
            Task task = this.taskList.get(index - 1);
            task.setNotDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no task with this index number.");
        }
    }
    /**
     * Removes the task with the specified index from the task list.
     *
     * @param number The index of the task to be removed.
     */
    public void delete(int number) throws DukeException {
        try {
            this.index = this.taskList.size();
            this.taskList.remove(number - 1);
            this.index--;

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException("☹ OOPS!!! There is no task with this index number.");
        }
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int getIndex() {
        return this.index;
    }

    public Task getTask(int index) throws DukeException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There is no task with this index number.");
        }
    }

    public int getSize() {
        return this.taskList.size();
    }
}
