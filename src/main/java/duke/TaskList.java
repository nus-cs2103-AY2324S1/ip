package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * A list to store the tasks.
 */
public class TaskList {
    /** A list to store the tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList that have an empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList that contains the tasks in the provided list.
     *
     * @param strings List containing the tasks.
     */
    public TaskList(ArrayList<String> strings) {
        this.tasks = new ArrayList<>();
        Parser parser = new Parser(this);
        for (String s : strings) {
            parser.parseFromFile(s);
        }
    }

    /**
     * Returns true if this list contains no elements.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specific position.
     *
     * @param i Index of the task to return.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds the task into the list.
     *
     * @param t Task to add.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes the task at the specific position.
     *
     * @param i Index of the task to remove.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * Returns a list of string representation of all the tasks for file writing.
     */
    public ArrayList<String> toStringInFile() {
        ArrayList<String> strings = new ArrayList<>();
        for (Task t : tasks) {
            strings.add(t.toStringInFile());
        }
        return strings;
    }
}
