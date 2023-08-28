package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task. A <code>Task</code> object corresponds to a task
 * described by a description and a boolean indicating whether the task is done.
 */
public class Task {
    protected String description; // The description of the task.
    protected boolean isDone; // The status of the task.

    /**
     * Returns a string representation of the date in the format of "h:mm a, MMM d
     * yyyy".
     * 
     * @param date The date to be converted.
     * @return A string representation of the date in the format of "h:mm a, MMM d
     *         yyyy".
     */

    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a task cannot be empty.");
        }
        
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a task cannot be empty.");
        }

        this.description = description;
        this.isDone = isDone;
    }

    protected static String getDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("h:mm a, MMM d yyyy"));
    }

    /**
     * Returns an icon representing the status of the task.
     * 
     * @return X if the task is done, a space otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task to be stored in the hard disk.
     * 
     * @return A string representation of the task to be stored in the hard disk.
     */
    public String toFileString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }
}