package ben;

/**
 * Represents an Unmark command
 */
public class UnmarkCommand extends Command {
    /**
     * The task to be unmarked
     */
    private Task task;

    /**
     * Takes in a task.
     *
     * @param task The task to be marked.
     */
    public UnmarkCommand(Task task) {
        this.task = task;
    }

    /**
     * Checks whether command causes the chatbot to exit.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the task.
     *
     * @param tasks The taskList
     * @param ui    The UI handling user interaction
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        task.unmark();
        return Ui.displayMessage("Okay! This task is not completed\n" + task + "\n");
    }
}
