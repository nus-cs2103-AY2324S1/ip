package sana;

/**
 * Represents a delete command for deleting tasks.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs an DeleteCommand object.
     *
     * @param cmd       The command type (e.g., "todo", "deadline", "event").
     * @param arguments The arguments associated with the command.
     */
    public DeleteCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes the delete command by deleting specified task from task list and disk.
     *
     * @param tasks The task list from where the task is deleted.
     * @param ui The user interface handling the command execution.
     * @param storage The storage manager for persisting task data.
     * @throws SanaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        String arguments = getArguments();
        if (arguments.isBlank()) {
            throw new SanaException("Error! Need to specify which task to delete");
        }

        Task deletedTask = tasks.delete(Integer.parseInt(arguments));
        System.out.println("Noted. I've removed this task:\n" + deletedTask.toString() + "\n"
                + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                + " in the list");
        tasks.update(storage);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return returns false as delete command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
