package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a task that has a deadline.
 */
public class Task {
    private final String description;
    private boolean isDone;
    private List<String> tags;

    /**
     * Creates a Task object.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Creates a Task object.
     * @return The description of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task that is to be saved by Storage.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public void addTags(String... tags) {
        this.tags.addAll(Arrays.asList(tags));
    }

    public String tagsToString() {
        StringBuilder tagsString = new StringBuilder();
        for (String tag : this.tags) {
            tagsString.append("#").append(tag).append(" ");
        }
        return tagsString.toString();
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + "\n" + tagsToString();
    }

    /**
     * Returns the description of the task that is to be saved by Storage.
     * @return The description of the task that is to be saved by Storage.
     */
    public String toSaveString() {
        System.out.printf("%s|%s|%s%n", isDone ? "1" : "0", description, tagsToString());
        return String.format("%s|%s|%s", isDone ? "1" : "0", description, tagsToString());
    }

}
