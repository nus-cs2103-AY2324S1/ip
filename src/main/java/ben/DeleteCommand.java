package ben;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command{
    /**
     * The task to be deleted.
     */
    private Task task;

    /**
     * Takes in a task to be deleted.
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
    public String execute(TaskList tasks, Ui ui) {
        tasks.delete(task);
        return Ui.displayMessage("Sure thing! This task has been removed\n" + task +
                "\nNow you have " + tasks.size() + " tasks left\n");
    }
}
