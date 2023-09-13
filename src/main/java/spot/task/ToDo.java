package spot.task;

import spot.exception.SpotException;

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

    /**
     * Updates the ToDo object's deadline.
     *
     * @param deadline Updated deadline.
     * @throws SpotException If the task has no deadline field.
     */
    public void updateDeadline(LocalDate deadline) throws SpotException {
        throw new SpotException("Spot thinks this task doesn't have a deadline!");
    }

    /**
     * Updates the ToDo object's start date.
     *
     * @param start Updated start date.
     * @throws SpotException If the task has no start date field.
     */
    public void updateStart(LocalDate start) throws SpotException {
        throw new SpotException("Spot thinks this task doesn't have a start date!");
    }

    /**
     * Updates the ToDo object's end date.
     *
     * @param end Updated end date.
     * @throws SpotException If the task has no end date field.
     */
    public void updateEnd(LocalDate end) throws SpotException {
        throw new SpotException("Spot thinks this task doesn't have an end date!");
    }
}
