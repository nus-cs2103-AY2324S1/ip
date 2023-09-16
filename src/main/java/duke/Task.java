package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Returns a Task object if provided with task description and associated tag ("T"/"D"/"E")
     *
     * @param description Task description (String)
     * @param tag "E" for Event, "T" for Todo, "D" for Deadline
     */
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTag() {
        return this.tag;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
