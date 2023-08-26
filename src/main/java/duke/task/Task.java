package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a boolean indicating whether the task has keyword in its description.
     *
     * @param keyword The keyword that is being searched in description.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }

    public String toSaveLine() {
        return ("");
    }
}
