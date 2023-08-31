package duke.tasks;

/**
 * Encapsulates a Todo in the chat bot.
 * Tasks without any date/time attached.
 *
 * @author Rayson
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String formatForStorage() {
        return String.format("T | %s", super.formatForStorage());
    }

}
