package duke.task;


/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    private String description;
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task for writing to a file.
     *
     * @return A string representation of the ToDo task for file output.
     */
    public String toStringFile() {
        return "T | " + super.toStringFile();
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "TODO";
    }
    /**
     * Compares this ToDo task to another object for equality.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo toDo) {
            return this.description.equals(toDo.description);
        }
        return false;
    }
}
