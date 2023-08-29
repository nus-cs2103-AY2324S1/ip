package pogo.tasks;

import pogo.tasks.exceptions.PogoInvalidTaskException;

import java.time.format.DateTimeFormatter;

/**
 * Task is an abstract class representing some kind of task, such as a deadline or todo.
 * A basic task contains a description and can be marked as done or not done.
 */
public abstract class Task {
    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Whether the task is done.
     */
    protected boolean isDone;

    public static DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructor for a Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) throws PogoInvalidTaskException {
        if (description.equals("")) {
            throw new PogoInvalidTaskException("Task description cannot be empty");
        }

        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for task done status.
     *
     * @return boolean isDone.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Getter for task description.
     *
     * @return String description.
     */
    public String getDescription() {
        return this.description;
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
    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusMessage() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Accepts a visitor that performs an action on the task.
     *
     * @param visitor Visitor to perform an action on the task.
     */
    public void accept(TaskVisitor visitor) {
        throw new UnsupportedOperationException("This task does not support visitors.");
    }

    @Override
    public String toString() {
        return this.description;
    }
}
