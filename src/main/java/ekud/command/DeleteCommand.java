package ekud.command;

/**
 * Represents the delete command used to delete a task.
 */
public final class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Creates a new delete command.
     * 
     * @param taskId The identifier of the task to delete.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Returns the identifier of the task to delete.
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
        return String.format("delete %d", taskId);
    }
}
