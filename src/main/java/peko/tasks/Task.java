package peko.tasks;

import peko.exceptions.InvalidTaskException;


/**
 * The Task class represents a basic task with a name and a completion status.
 * It serves as the base class for various task types, such as ToDos, Deadlines, and Events.
 */
public class Task {
    protected String name;
    protected boolean status;

    /**
     * Constructs a Task object with the specified name.
     *
     * @param s The name of the task.
     * @throws InvalidTaskException If the provided name is blank or empty.
     */
    public Task(String s) throws InvalidTaskException {
        if (s.isBlank()) {
            throw new InvalidTaskException();
        }
        this.name = s;
        status = false;
    }


    /**
     * Returns a formatted string representation of the task, including its completion status and name.
     *
     * @return A formatted string representing the task, with a checkbox indicating completion status.
     */
    @Override
    public String toString() {
        String out = status ? "[X] " : "[ ] ";
        return out + name;
    }

    /**
     * Marks the task as completed by setting its completion status to true.
     */
    public void setMark() {
        status = true;
    }

    /**
     * Unmarks the task as completed by setting its completion status to false.
     */
    public void setUnmark() {
        status = false;
    }

    /**
     * Checks if the task's name contains a specific string.
     *
     * @param s The string to search for in the task's name.
     * @return True if the task's name contains the specified string; otherwise, false.
     */
    public boolean hasString(String s) {
        return name.contains(s);
    }

    /**
     * Converts the task object to a string for storage in a text file.
     *
     * @return A string representation of the task suitable for storage in a text file.
     */
    public String toStore() {
        String state = status ? "0" : "1";
        return state + " | " + this.name;
    }
}
