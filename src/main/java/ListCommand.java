/**
 * Command to list the current tasks stored.
 */
public class ListCommand extends Command{
    /**
     * Constructor for a list command.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the current list command.
     * @param tasklst current list of tasks
     * @param ui instance of user interface
     * @param storage instance of storage to read and write files
     */
    @Override
    public void execute(TaskList tasklst, Ui ui, Storage storage) {
        tasklst.displayTasks();
    }

    /**
     * Checks if the current task is an exit task.
     * @return false since task is not an exit task
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
