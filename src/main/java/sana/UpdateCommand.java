package sana;

/**
 * Represents an update command class
 */
public class UpdateCommand extends Command {

    /**
     * Constructs an Update Command object.
     *
     * @param command      The command type (e.g., "todo", "deadline", "event").
     * @param arguments The arguments associated with the command.
     */
    public UpdateCommand(String command, String arguments) {
        super(command, arguments);
    }

    /**
     * Executes the Update Command to update a task in the task list.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage manager.
     * @return A response message after executing the command.
     * @throws SanaException If there's an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SanaException {
        String[] parsedArguments = getArguments().split(" ", 2);
        int taskId = Integer.parseInt(parsedArguments[0]);
        String newTaskArgument = parsedArguments[1];

        String response = tasks.updateTasksList(taskId, newTaskArgument);
        tasks.update(storage);
        return response;
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
