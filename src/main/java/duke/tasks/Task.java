package duke.tasks;

import java.time.LocalDateTime;

/**
 * Encapsulates a Duke.Tasks.Task in the Chat bot.
 *
 * @author Rayson
 */
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatus(), description);
    }

    public String formatForStorage() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    public boolean isWithinDateRange(LocalDateTime date) {
        return false;
    }

    public boolean isRelatedContent(String keyword) {
        return description.contains(keyword);
    }

}
