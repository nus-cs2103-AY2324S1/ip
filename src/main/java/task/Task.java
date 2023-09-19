package task;

import exceptions.DukeException;

/**
 * The Task class is an abstract base class for different types of tasks.
 * It provides methods to mark tasks as done and generate file strings.
 */
public abstract class Task {

    protected Boolean isDone;

    /**
     * Constructs a Task object with the specified done status.
     *
     * @param isDone The done status of the task.
     */
    public Task(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }


    /**
     * Checks if title of task contains the query.
     *
     * @return A Boolean value representing if title contains query.
     */
    public abstract Boolean compareTitle(String query);

    /**
     * Generates a string representation of the task for storage in a file.
     *
     * @return A formatted string representing the task.
     */
    public String toFileString() {
        return "task";
    }

    /**
     * Edits the title of the task.
     *
     * @param edit The new title for the task.
     */
    public void editTitle(String edit) {
    }

    /**
     * Edits the deadline of the task.
     *
     * @param newDeadline The new deadline for the task.
     * @throws DukeException If there is an issue editing the deadline.
     */
    public void editDeadline(String newDeadline) throws DukeException {
    }

    /**
     * Edits the start time of the task.
     *
     * @param newStart The new start time for the task.
     * @throws DukeException If there is an issue editing the start time.
     */
    public void editStart(String newStart) throws DukeException {
    }

    /**
     * Edits the end time of the task.
     *
     * @param newEnd The new end time for the task.
     * @throws DukeException If there is an issue editing the end time.
     */
    public void editEnd(String newEnd) throws DukeException {
    }
}
