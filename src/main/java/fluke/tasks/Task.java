package fluke.tasks;

import fluke.exceptions.EmptyDescriptionException;
import fluke.exceptions.FlukeException;

/**
 * A task is something which is to be done. It contains a description and whether it is done.
 */
public abstract class Task {

    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Whether the task is done.
     */
    protected boolean isDone = false;

    /**
     * Constructs a task with a description.
     * @param description Description of the task.
     * @throws FlukeException when the description given is empty.
     */
    protected Task(String description) throws FlukeException {
        String trimmedDescription = description.trim();
        if (trimmedDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        this.description = trimmedDescription;
    }

    /**
     * Constructs a task with a description and whether it is done.
     * @param description Description of the task.
     * @param isDone Whether the task is done.
     * @throws FlukeException when the description given is empty.
     */
    protected Task(String description, boolean isDone) throws FlukeException {
        String trimmedDescription = description.trim();
        if (trimmedDescription.equals("")) {
            throw new EmptyDescriptionException();
        }
        this.description = trimmedDescription;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks whether this task has a keyword
     * @param keyword the keyword to check
     * @return whether the keyword is present in the task description.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns a string representation of a task.
     * @return a string representation containing whether the task is completed and what it is.
     */
    public String toString() {
        String doneString = this.isDone ? "X" : " ";
        return "[" + doneString + "] " + this.description;
    }
}
