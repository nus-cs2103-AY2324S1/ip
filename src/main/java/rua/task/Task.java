package rua.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a generic Task.
 */
public class Task {
    protected final String description;
    protected Boolean isMarked;
    protected ArrayList<String> tags;

    /**
     * Constructs a Task object (assuming no tag).
     *
     * @param description A String to describe the task.
     * @param isMarked A boolean to indicate whether it is marked.
     */
    Task(String description, Boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a Task object (assuming unmarked and no tag).
     *
     * @param description A String to describe the task.
     */
    Task(String description) {
        this.description = description;
        this.isMarked = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a Task object.
     *
     * @param description A String to describe the task.
     * @param isMarked A boolean to indicate whether it is marked.
     * @param tags An arraylist of tags.
     */
    Task(String description, Boolean isMarked, ArrayList<String> tags) {
        this.description = description;
        this.isMarked = isMarked;
        this.tags = tags;
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
     * Returns the ArrayList of tags of the Task.
     *
     * @return A ArrayList of tags.
     */
    public ArrayList<String> getTags() {
        return this.tags;
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
        this.isMarked = true;
        return this;
    }

    /**
     * Sets this task to be unmarked.
     *
     * @return A new Task object with the same description but it is unmarked.
     */
    public Task setUnmarked() {
        this.isMarked = false;
        return this;
    }

    /**
     * Adds tags to a task.
     *
     * @param newTags Tags to be added.
     */
    public void addTags(ArrayList<String> newTags) {
        this.tags.addAll(newTags);
    }

    /**
     * Clears all tags of a task.
     */
    public void clearTags() {
        this.tags.clear();
    }

    /**
     * Deletes a set of tags from a task.
     */
    public void deleteTags(ArrayList<String> discardedTags) {
        for (String tag : discardedTags) {
            this.tags.remove(tag);
        }
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
     * @return A string representing this task in the format: [ marked indicator ] description.
     */
    @Override
    public String toString() {
        final String markStatus = this.isMarked ? "X" : " ";
        final String tagStatus = tags.isEmpty() ? "[ ]" : tags.toString();
        return "[" + markStatus + "]" + tagStatus + " " + this.description;
    }
}
