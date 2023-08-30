/**
 * Command to list tasks in task list.
 */
public class ListCommand extends Command {
    /**
     * Instantiates a list command.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Lists stored tasks in chronological order of addition.
     * @param tasks ArrayList of tasks.
     * @param ui User interaction handler.
     * @param storage File loading and saving handler.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }
}
