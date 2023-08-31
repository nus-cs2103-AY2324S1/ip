package sana;

/**
 * Represents a command for marking a task as done.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an unmarkCommand object.
     *
     * @param cmd       The command type (e.g., "todo", "deadline", "event", "mark").
     * @param arguments The arguments associated with the command.
     */
    public UnmarkCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes unmark command by marking the specified taks in the list as NOT done and updates changes in file.
     *
     * @param tasks The task list on which the specified task is marked as done.
     * @param ui The user interface handling the command execution.
     * @param storage The storage manager for persisting task data.
     * @throws SanaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        tasks.unmark(Integer.parseInt(getArguments()));
        tasks.update(storage);
    }

    /**
     * Checks if the ListCommand is an exit command.
     *
     * @return always returns false as ListCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
