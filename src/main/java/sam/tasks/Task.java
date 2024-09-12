package sam.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected List<String> tags;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to add (e.g., "#fun").
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Adds a list of tags to the task.
     *
     * @param newTags The list of tags to add (e.g., ["#fun", "#important"]).
     */
    public void addTags(List<String> newTags) {
        tags.addAll(newTags);
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Represents the task's status when printing.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[").append(getStatusIcon()).append("] ").append(this.description);

        // Append tags, if any
        if (!tags.isEmpty()) {
            stringBuilder.append("  ");
            for (String tag : tags) {
                stringBuilder.append(tag).append(" ");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Represents the task's encoded format in the hard disk.
     */
    public String toFileString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| ").append(this.isDone ? "1" : "0").append(" | ").append(this.description);

        // Append tags, if any
        if (!tags.isEmpty()) {
            stringBuilder.append(" | Tags: ");
            for (String tag : tags) {
                stringBuilder.append(tag).append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
