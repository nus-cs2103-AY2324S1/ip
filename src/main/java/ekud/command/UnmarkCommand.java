package ekud.command;

/**
 * Represents the unmark command used to mark a task as not done.
 */
public final class UnmarkCommand extends Command {
    private final int taskId;

    /**
     * Creates a new unmark command.
     * 
     * @param taskId The identifier of the task to unmark.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Returns the identifier of the task to unmark.
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
        return String.format("unmark %d", taskId);
    }
}
