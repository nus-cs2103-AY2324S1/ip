package duke;

/**
 * Represents a Todo task, a subclass of Task.
 */
public class Todos extends Task {
    /**
     * Creates a new instance of a ToDo task with the provided description.
     *
     * Creates a new instance of a ToDo task with the provided description.
     */
    public Todos(String description) {
        super(description);

        // Assert that the description is not null or empty.
        assert description != null && !description.trim().isEmpty()
                : "Description should not be null or empty";
    }

    /**
     * Creates a new instance of a Todo task with the provided description, completion status, and notes.
     *
     * @param description A string representing the description of the Todo task.
     * @param isDone A boolean indicating whether the task is done.
     * @param notes A string containing notes related to the task.
     */
    public Todos(String description, Boolean isDone, String notes) {
        super(description, isDone, notes);

        // Assert that the description is not null or empty.
        assert description != null && !description.trim().isEmpty()
                : "Description should not be null or empty";
    }

    /**
     * Returns the saving format of the ToDo task.
     *
     * @return The string representation of the ToDo task suitable for saving to a file.
     */
    @Override
    public String getSavingFormat() {
        // Assert that the description is not null or empty before saving.
        assert description != null && !description.trim().isEmpty()
                : "Description should not be null or empty when saving";

        //task type + status + description + notes
        return "[T] | [" + getStatusIcon() + "] | " + description + " | " + getNotes();
    }

    /**
     * Converts a string array to a Todo task.
     *
     * @param split A string array containing the different parts of the Todo task (status, description, notes).
     * @return A new instance of a Todo task based on the provided string array.
     */
    public static Todos toTask(String[] split) {
        return new Todos(split[2], split[1].contains("[X]"), split.length == 4 ? split[3] : "");
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return The string representation of the ToDo task in the format: [T][status icon] description.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
