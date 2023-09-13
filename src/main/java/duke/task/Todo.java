package duke.task;

import duke.DukeException;

/**
 * Represents a todo task, containing a description.
 */
public class Todo extends Task {

    /**
     * Returns a new Todo task containing the message.
     *
     * @param message The description for the to-do.
     */
    public Todo(String message) {
        super(message);
    }

    /**
     * Updates a todo task based on the specified update type and value.
     *
     * @param type The UpdateType to update the task with.
     * @param newValue The new value to update the task with.
     * @throws DukeException If the type and new value parameters are invalid.
     */
    @Override
    public void update(UpdateType type, String newValue) throws DukeException {
        switch (type) {
        case DESCRIPTION:
            message = newValue;
            break;
        case DATE1:
            // fallthrough
        case DATE2:
            throw new DukeException("Cannot update: Todos do not have dates!");
            // exception thrown, no break statement needed
        default:
            throw new DukeException("Invalid update type!");
            // exception thrown, no break statement needed
        }
    }

    /**
     * Returns a String containing information within the Todo task, formatted to be saved.
     *
     * @return The to-do task, formatted as a String to be saved in the save file.
     */
    @Override
    public String toSaveFormatString() {
        return "T | " + getStatusNumber() + " | " + message;
    }

    /**
     * Returns a String representation of the Todo task, formatted for output in the application.
     *
     * @return The to-do task, formatted as a String for output in the application.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + message;
    }

    @Override
    public Todo clone() {
        return new Todo(message);
    }
}
