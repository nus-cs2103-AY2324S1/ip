package rua.task;

import java.time.LocalDate;

/**
 * Represents a Task. A Task object corresponds to
 * a task represented by a String description and a boolean
 * to indicate whether it is marked.
 */
public class Task {
    protected final String description;
    protected final Boolean isMarked;

    /**
     * Constructs a Task object.
     *
     * @param description A String to describe the task.
     * @param marked A boolean to indicate whether it is marked.
     */
    Task(String description, Boolean marked) {
        this.description = description;
        this.isMarked = marked;
    }

    /**
     * Constructs a Task object (assuming unmarked).
     *
     * @param description A String to describe the task.
     */
    Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Returns the type of the Task, The type is None by default.
     *
     * @return The task type (None here by default).
     */
    public String getType() {
        return "None";
    }

    /**
     * Returns the description of the Task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the marked/unmarked status of the Task.
     *
     * @return A boolean to indicate whether it is marked or not.
     */
    public Boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Checks whether a task happens on the given date.
     *
     * @param date A given date which we will check whether this task happens on that date.
     * @return A boolean to indicate whether it happens on that day (False by default if not applicable).
     */
    public Boolean isHappeningOnThatDate(LocalDate date) {
        return false;
    }

    /**
     * Sets this task to be marked.
     *
     * @return A new Task object with the same description but it is marked.
     */
    public Task setMarked() {
        return new Task(this.description, true);
    }

    /**
     * Sets this task to be unmarked.
     *
     * @return A new Task object with the same description but it is unmarked.
     */
    public Task setUnmarked() {
        return new Task(this.description, false);
    }

    /**
     * Compares the task with other objects and return true if they are the same task.
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }
        Task c = (Task) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description)
                && c.isMarked.equals(this.isMarked);
    }

    /**
     * Returns a string to represent this task.
     *
     * @return A string representing this task in the format:
     * [ marked indicator ] description.
     */
    @Override
    public String toString() {
        return "[" +  (this.isMarked ? "X" : " ") + "] " + this.description;
    }
}
