package ben;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command{
    /**
     * The task to be marked.
     */
    Task task;

    /**
     * Takes in a task.
     *
     * @param task The task to be marked.
     */
    public MarkCommand(Task task) {
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
     * Executes the mark command.
     *
     * @param tasks The taskList
     * @param ui The UI handling user interaction
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        task.mark();
        return Ui.displayMessage("Nice! This task is completed\n" + task + "\n");
    }
}
