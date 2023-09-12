package peko.tasks;

import peko.exceptions.InvalidTaskException;
import peko.tasks.Task;

/**
 * Represents a "To-Do" task.
 * A "To-Do" task is a simple task without a specific deadline or event time.
 */
public class ToDos extends Task {
    char type = 'T';

    /**
     * Constructs a "To-Do" task with the specified description.
     *
     * @param s The description of the task.
     * @throws InvalidTaskException If the description is empty or invalid.
     */
    public ToDos(String s) throws InvalidTaskException {
        super(s);
        this.name = s;
    }

    /**
     * Returns a string representation of the "To-Do" task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    /**
     * Converts the "To-Do" task to a string suitable for storage in a text file.
     *
     * @return A formatted string representation of the task for storage.
     */
    public String toStore() {
        String state = status ? "0" : "1";
        return "T" + " | " + state + " | " + this.name;
    }
}
