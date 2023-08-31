package ekud.command;

/**
 * Represents the mark command used to mark a task as done.
 */
public final class MarkCommand extends Command {
    private final int taskId;

    /**
     * Creates a new mark command.
     * 
     * @param taskId The identifier of the task to mark.
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Returns the identifier of the task to mark.
     * 
     * @return The task identifier.
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Returns the string representation of the command.
     * This is identical (excluding whitespace) to how the user would type it into
     * the CLI.
     * 
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("mark %d", taskId);
    }
}
