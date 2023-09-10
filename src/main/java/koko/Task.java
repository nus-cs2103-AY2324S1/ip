package koko;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given name and sets its completion status to false.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public static Task fromFileFormat(String line) throws DukeException {
        String[] parts = line.split("\\|", -1); // -1 to keep trailing empty strings
        if (parts.length < 3) {
            return null;
        }

        String taskType = parts[0].trim();
        switch (taskType) {
            case "T":
                return Todo.fromFileFormat(parts);
            case "D":
                return Deadline.fromFileFormat(parts);
            case "E":
                return Event.fromFileFormat(parts);
            default:
                return null;
        }
    }

    public abstract String toFileFormat();

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting its completion status to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task to be displayed in a Todo List.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }

}
