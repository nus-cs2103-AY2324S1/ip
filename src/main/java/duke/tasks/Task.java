package duke.tasks;

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a <code>Task</code> object.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Initially, the task is not done
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     * @return The completion status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Converts a task to file format.
     * @return The description of the task.
     */
    public String toFileFormat() {
        return (getIsDone() ? "1" : "0") + " | " + getDescription();
    }

    /**
     * Converts a task from file format depending on what kind of task it is.
     * @return The description of the task.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                return Todo.fromFileFormat(line);
            case "D":
                return Deadline.fromFileFormat(line);
            case "E":
                return Event.fromFileFormat(line);
            default:
                return null;
        }
    }

    /**
     * Returns the string representation of a task.
     * @return The string representation of a task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

