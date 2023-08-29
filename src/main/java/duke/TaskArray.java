package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * An ordered collection to store tasks.
 */
public interface TaskArray {
    /**
     * Returns true if this list contains no tasks.
     */
    boolean isEmpty();

    /**
     * Returns the size of the list.
     */
    int size();

    /**
     * Returns the task at the specific position.
     *
     * @param i Index of the task to return.
     */
    Task get(int i);

    /**
     * Adds the task into the list.
     *
     * @param t Task to add.
     */
    void add(Task t);

    /**
     * Removes the task at the specific position.
     *
     * @param i Index of the task to remove.
     */
    void remove(int i);

    /**
     * Returns a list of string representation of all the tasks for file writing.
     */
    ArrayList<String> toStringInFile();
}
