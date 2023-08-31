package sana;

/**
 * Represents a ListCommand that handles displaying the list of tasks.
 *
 * This command retrieves the list of tasks and displays them to the user. If the list
 * is empty, it throws a {@link SanaException} indicating that the list is empty.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     *
     * @param cmd       The command type (e.g., "todo", "deadline", "event").
     * @param arguments The arguments associated with the command.
     */
    public ListCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes list command by printing all tasks in the list.
     *
     * @param tasks The task list on which the command's action is performed.
     * @param ui The user interface handling the command execution.
     * @param storage The storage manager for persisting task data.
     * @throws SanaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        if (tasks.toString().isBlank()) {
            throw new SanaException("Your list is empty! Add tasks first to display list");
        }

        TaskList updatedTasks = new TaskList(storage.load());
        System.out.println(updatedTasks);
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

    /**
     * Compares this ListCommand object with the specified object for equality.
     *
     * The comparison is based on the equality of the command strings represented
     * by the two ListCommand objects.
     *
     * @param obj the object to compare to
     * @return true if the given object is an instance of ListCommand and has
     *         the same command string as this ListCommand object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Command) {
            Command c = (Command) obj;

            if (c == null || this == null) {
                return false;
            }

            return this.getCmd().equals(c.getCmd());
        }
        return false;
    }
}
