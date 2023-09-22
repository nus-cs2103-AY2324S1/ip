package sana;

/**
 * Represents a command for marking a task as done.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an unmarkCommand object.
     *
     * @param command       The command type (e.g., "todo", "deadline", "event", "mark").
     * @param arguments The arguments associated with the command.
     */
    public UnmarkCommand(String command, String arguments) {
        super(command, arguments);
    }

    /**
     * Executes unmark command by marking the specified taks in the list as NOT done and updates changes in file.
     *
     * @param tasks The task list on which the specified task is marked as done.
     * @param storage The storage manager for persisting task data.
     * @throws SanaException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SanaException {
        if  (getArguments() == "") {
            throw new SanaException("No such command!");
        }
        String response = tasks.unmark(Integer.parseInt(getArguments()));
        tasks.update(storage);
        return response;
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
