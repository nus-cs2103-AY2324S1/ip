package taskstuff;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import duke.DukeException;


/**
 * A class to hold a list of tasks and perform operations on
 * this list.
 */
public class TaskList {

    /** An ArrayList to hold tasks entered by the User. */
    private ArrayList<Task> tasks;

    /** An ArrayList to hold the undos. */
    private ArrayList<Consumer<TaskList>> undos;

    /**
     * Initialises the tasks list to an empty array List.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.undos = new ArrayList<Consumer<TaskList>>();
    }

    /**
     * Initialises the tasks list with the given array list.
     *
     * @param tasks The array list to initialise using.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.undos = new ArrayList<Consumer<TaskList>>();
    }


    /**
     * Adds the given task to the tasks ArrayList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        int size = this.getSize();
        undos.add(x -> x.tasks.remove(size - 1));
    }

    /**
     * Returns a Stream of String describing the tasks present in tasks ArrayList.
     *
     * @return Returns a Stream of String describing this taskList.
     */
    public Stream<String> getTasks() {
        return this.tasks.stream().map(Task::toString);
    }

    /**
     * Marks a given Task and throws duke.DukeException if invalid index.
     *
     * @param index The location of the task in tasks ArrayList.
     * @throws DukeException If invalid index provided.
     */
    public void markTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("The index is not a valid index. Try again.");
        }
        tasks.get(index - 1).setAsDone();
        undos.add(x -> x.tasks.get(index - 1).setAsNotDone());
    }

    /**
     * Unmarks a given task.Task as not done and throws DukeException if invalid index.
     *
     * @param index The location of the task in tasks ArrayList.
     * @throws DukeException If invalid index provided.
     */
    public void unmarkTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("The index is not a valid index. Try again.");
        }
        tasks.get(index - 1).setAsNotDone();
        int size = this.getSize();
        undos.add(x -> x.tasks.get(index - 1).setAsDone());
    }

    /**
     * Deletes a given Task from the tasklist and throws DukeException if invalid index.
     * Returns the deleted task's string representation.
     *
     * @param index The location of the task in tasks ArrayList.
     * @return String representation of deleted task if successful.
     * @throws DukeException If invalid index provided.
     */
    public String deleteTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("The index is not a valid index. Try again.");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        undos.add(x -> x.tasks.add(index - 1, task));
        return task.toString();
    }


    /**
     * Returns the size of the taskList.
     *
     * @return Returns the size of the current taskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the string representation of task at index i.
     * If invalid index DukeException is thrown.
     *
     * @param index The index of task whose string representation is needed.
     * @throws DukeException If invalid index is provided.
     */
    public String getTask(int index) throws DukeException {
        if (index > tasks.size() || index <= 0) {
            throw new DukeException("");
        }
        return this.tasks.get(index - 1).toString();
    }

    /**
     * Returns a Stream of String containing name of tasks which contain keyword.
     *
     * @param s The keyword to match in task's name;
     * @return Returns a Stream of String containing tasks whose names contain the keyword.
     */
    public Stream<String> findTasks(String s) {
        return this.tasks.stream().filter(x -> x.hasKeyWord(s)).map(Task::toString);
    }


    /**
     * Undoes the last undoable task.
     *
     * @throws DukeException If there is nothing to undo.
     */
    public void undo() throws DukeException {
        if (undos.size() == 0) {
            throw new DukeException("There is nothing to undo.");
        }
        Consumer<TaskList> c = undos.get(undos.size() - 1);
        undos.remove(undos.size() - 1);
        c.accept(this);
    }
}
