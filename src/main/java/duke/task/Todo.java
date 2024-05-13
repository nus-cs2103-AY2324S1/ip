package duke.task;

/**
 * Represents a to-do task to be used by the Duke chat-bot.
 */
public class Todo extends Task {
    /**
     * Creates a To-do object.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description, false);
    }

    /**
     * Creates a To-do object with done status as specified.
     *
     * @param description The description of the to-do task.
     * @param isDone      Whether the to-do task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the to-do task encoded for storage for the Duke chat-bot.
     *
     * @return The to-do task encoded for storage for the Duke chat-bot.
     */
    @Override
    public String encodeTask() {
        return String.format("T;%s;%s", this.isDone ? "X" : " ", this.description);
    }
}
