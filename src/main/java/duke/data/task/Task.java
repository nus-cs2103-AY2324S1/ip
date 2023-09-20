package duke.data.task;

import duke.exception.InvalidInputException;

/**
 * Represents a task.
 */
public abstract class Task {
    protected Tags tags = new Tags();
    protected Description description;
    protected boolean isDone = false;
    protected String userInputString;
    public boolean hasTag(String tag) {
        return this.tags.hasTag(tag);
    }
    public void addTag(String tag) throws InvalidInputException {
        this.tags.addTag(tag);
    }
    public void removeTag(String tag) throws InvalidInputException {
        this.tags.removeTag(tag);
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = new Description(description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + ".  (tags: " + this.tags + ")";
    }
    public String getTaskRepresentation() throws NullPointerException {
        return this.userInputString + "/isMarked " + this.isDone + "/tags " + this.tags.toString();
    }
    public void setUserInputString(String userInputString) {
        this.userInputString = userInputString;
    }

    public boolean hasKeyword(String keyword) {
        return this.description.hasKeyword(keyword);
    }
}
