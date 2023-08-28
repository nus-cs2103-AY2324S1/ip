package spot.task;

import java.time.LocalDate;

/**
 * Represents a todo.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object.
     *
     * @param description Description of the ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo object.
     *
     * @param description Description of the ToDo object.
     * @param isDone Boolean representing the state of completion of the ToDo object.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns String representation of the ToDo object.
     *
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns representation of the ToDo object to be stored
     * in a text file within the hard disk.
     *
     * @return Representation of the ToDo object to be stored.
     */
    @Override
    public String toLine() {
        return " T | " + super.toLine();
    }

    /**
     * Returns whether the ToDo object falls on the specified date.
     *
     * @param date Specified date.
     * @return Boolean representing whether the ToDo object falls on the specified date.
     */
    @Override
    public boolean fallsOn(LocalDate date) {
        return false;
    }
}
