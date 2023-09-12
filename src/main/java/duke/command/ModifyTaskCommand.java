package duke.command;

/**
 * Represents a command to modify an existing task.
 */
public abstract class ModifyTaskCommand extends Command {
    protected int taskIndex;

    /**
     * Returns an instance of {@code ModifyTaskCommand} with the given task index.
     *
     * @param taskIndex The index of the task.
     */
    public ModifyTaskCommand(String taskIndex) {
        try {
            this.taskIndex = Integer.parseInt(taskIndex);
        } catch (NumberFormatException e) {
            assert false;
        }
    }
}
