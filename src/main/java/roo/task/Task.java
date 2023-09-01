package roo.task;

import java.time.LocalDateTime;

import roo.RooException;

/**
 * Represents a task in the Duke application.
 * Tasks can be marked as done or undone, and have a description and completion status.
 */
public abstract class Task {
    private boolean isFinish;
    private final String task;

    /**
     * Constructs a Task object with the given description. The task is initialized as not finished.
     * @param task The description of the task.
     * @throws RooException If the task description is empty or consists only of spaces.
     */
    public Task(String task) throws RooException {
        if (task.isEmpty() || task.equals(" ")) {
            throw new RooException("Description is EMPTY!!!\n");
        }
        this.isFinish = false;
        this.task = task;
    }

    /**
     * Constructs a Task object with the given description.
     * @param task   The description of the task.
     * @param isFinish The completion status of the task.
     */
    public Task(String task, boolean isFinish) throws RooException {
        this.isFinish = isFinish;
        this.task = task;
    }

    /**
     * Checks if the task is marked as done.
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean done() {
        return this.isFinish;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isFinish = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isFinish = false;
    }

    /**
     * Abstract method to be implemented by subclasses. Returns the date associated with the task.
     * @return The date associated with the task.
     */
    public abstract LocalDateTime getDate();

    /**
     * Returns a string representation of the task.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if (this.isFinish) {
            return "[x] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
