package grumpygordon.tasks;

import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.parser.Parser;

/**
 * Represents a generic task added by the user.
 */
public abstract class Task {

    /**
     * Description of task.
     */
    protected String description;

    /**
     * Boolean that represents whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructor used to create a task.
     * @param description Description of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of a task.
     * @return Status icon of a task
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of a Task.
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Returns the Task from the save format.
     * @return Task generated from the save format
     */
    public static Task fromSaveFormat(String line) throws GrumpyGordonException {
        return Parser.parseStringToTask(line);
    }

    /**
     * Returns the format in which a Task will be saved.
     * @return String representation of the save format of a Task.
     */
    public abstract String toSaveFormat();
}
