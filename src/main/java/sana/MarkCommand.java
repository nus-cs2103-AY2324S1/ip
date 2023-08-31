package sana;

/**
 * Represents a command for marking a task as done.
 */
public class MarkCommand extends Command {

    /**
     * Constructs an MarkCommand object.
     *
     * @param cmd       The command type (e.g., "todo", "deadline", "event", "mark").
     * @param arguments The arguments associated with the command.
     */
    public MarkCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes mark command by marking the specified taks in the list as done and updates changes in file.
     *
     * @param tasks The task list on which the command's action is performed.
     * @param ui The user interface handling the command execution.
     * @param storage The storage manager for persisting task data.
     * @throws SanaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        tasks.mark(Integer.parseInt(getArguments()));
        tasks.update(storage);
    }

    /**
     * Checks if the MarkCommand is an exit command.
     *
     * @return always returns false as MarkCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
