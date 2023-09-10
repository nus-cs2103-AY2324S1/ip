package koko;

/**
 * Represents a generic Task with a name and completion status.
 */
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

    /**
     * Generates a Task object from its file representation.
     * @param line The line from the file representing a Task.
     * @return A Task object.
     * @throws DukeException If the line is in an invalid format.
     */
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

    /**
     * Returns the file representation of the task.
     * @return A string representing the task in the file format.
     */
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
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
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
