package ben;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command{
    /**
     * The task to be deleted.
     */
    private final Task task;

    /**
     * Constructor that takes in a task to be deleted.
     *
     * @param task The task to be deleted.
     */
    public DeleteCommand(Task task) {
        this.task = task;
    }

    /**
     * Checks whether command causes the chatbot to exit.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes deleting the task from the taskList.
     *
     * @param tasks The taskList
     * @param ui The UI handling user interaction
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.delete(task);
    }
}
