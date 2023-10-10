package duke.utils;

import java.time.LocalDateTime;

/**
 * The ToDo class represents a task of type "Todo" in the Duke application.
 * It extends the Task class and provides specific functionality for Todo tasks.
 */
public class Todo extends Task {

    private static final LocalDateTime MAX_DATE = LocalDateTime.MAX;

    /**
     * Constructs a new Todo object with a title.
     *
     * @param title The title of the Todo task.
     */
    protected Todo(String title) {
        super(title, Task.Type.TODO);
    }

    /**
     * Constructs a new Todo object with a title and a marked status.
     *
     * @param title  The title of the Todo task.
     * @param marked true if the task is marked as completed, false otherwise.
     */
    protected Todo(String title, boolean marked) {
        this(title);
        if (marked) {
            this.mark();
        }
    }

    /**
     * Creates a Todo object from an array of string arguments.
     *
     * @param args The array of string arguments containing task data.
     * @return A Todo object created from the provided arguments.
     * @throws InvalidFileDataException if the input arguments are invalid.
     */
    protected static Todo of(String[] args) throws InvalidFileDataException {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        return new Todo(title, marked);
    }

    /**
     * Converts the Todo object to a CSV (Comma-Separated Values) string.
     *
     * @return A CSV string representation of the Todo object.
     */
    @Override
    public String toCsv() {
        return FileIO.joinCsv(this.type(), this.marked(), this.name());
    }

    /**
     * Returns maximum date possible, since the deadline is non-existent
     * @return A LocalDateTime representation of the maximum date possible
     */
    @Override
    public LocalDateTime getDeadline() {
        return Todo.MAX_DATE;
    }

    @Override
    public String toString() {
        return this.type() + super.toString();
    }
}
