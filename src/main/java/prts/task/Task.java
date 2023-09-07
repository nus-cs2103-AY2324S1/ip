package prts.task;

import java.io.Serializable;

/**
 * Represents an abstract Task item that can be added to the TaskList by the user.
 * Keeps track of whether the task is completed, and stores a description that can
 * be displayed to the user.
 */
public abstract class Task implements Serializable {

    private boolean isDone;
    protected String name;

    /**
     * Constructs a Task object. Always initializes the Task as not-yet-done.
     * @param name The description of the Task to be created.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks a Task as done.
     * @throws AlreadyMarkedException If the Task is already marked as done.
     */
    public void markDone() throws AlreadyMarkedException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new AlreadyMarkedException();
        }
    }

    /**
     * Marks a Task as not yet done.
     * @throws AlreadyUnmarkedException If the Task is already not yet done.
     */
    public void markUndone() throws AlreadyUnmarkedException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new AlreadyUnmarkedException();
        }
    }

    /**
     * Returns a string representation of a Task object, to be displayed to the user.
     * @return The string representation displayed to the user.
     */
    @Override
    public String toString() {
        return this.isDone ? "[X] " + name : "[ ] " + name;
    }

    /**
     * Returns the message that should be displayed to the user when this Task is added
     * to the TaskList.
     * @return The message displayed to the user.
     */
    public abstract String getAddMessage();

    /**
     * Searches the description of the Task to find the given search term.
     * @param searchTerm The term to search for.
     * @return True if the search term is found, false otherwise.
     */
    public abstract boolean contains(String searchTerm);

}
