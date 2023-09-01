package duke;

/**
 * Todo keep tracks of tasks without deadlines.
 */
public class ToDo extends Task {

    /**
     * Creates a todo from a string representing the description of the task.
     * If the description is empty, it throws a DukeException.
     *
     * @param description The description of the task.
     * @return A ToDo object representing the created task.
     * @throws DukeException If the description is empty.
     */
    public static ToDo create(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("Err: Empty Description");
        }
        return new ToDo(description);
    }

    /**
     * Constructs a todo with a string representing the description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gives a string representation of the task in file format.
     *
     * @return A string representation of the task in file format.
     */
    @Override
    public String fileString() {
        return String.format(
                "todo %d %s",
                super.isDone ? 1 : 0,
                super.description
        );
    }

    /**
     * Gives a string representation of the task in display format.
     *
     * @return A string representation of the task in display format.
     */
    @Override
    public String toString() {
        return String.format(
                "[T][%s] %s",
                super.getStatusIcon(),
                super.description
        );
    }
}
