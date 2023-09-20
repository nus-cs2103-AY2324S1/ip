package duke.tasks;

/**
 * A class that encapsulates a todo item in the chat bot.
 * Todo tasks do not have any date/time attached.
 *
 * @author Rayson
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo with the given description.
     *
     * @param description The description of the todo item.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo with the given description and isDone status.
     *
     * @param description The description of the todo item.
     * @param isDone Whether the todo item is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of this ToDo.
     *
     * @return A string representation of this ToDo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a string representation of this ToDo for storage.
     *
     * @return A string representation of this ToDo for storage.
     */
    @Override
    public String formatForStorage() {
        return String.format("T | %s", super.formatForStorage());
    }

}
