package sana;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     *
     * @param cmd       The command type (e.g., "todo", "deadline", "event").
     * @param arguments The arguments associated with the command.
     */
    public ExitCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes the exit command by printing out the exit message.
     *
     * @param tasks      The task list on which the command's action is performed.
     * @param ui         The user interface handling the command execution.
     * @param storage    The storage manager for persisting task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return returns false as add command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Compares this Command object with the specified object for equality.
     *
     * The comparison is based on the equality of the command strings represented
     * by the two Command objects.
     *
     * @param obj the object to compare to
     * @return true if the given object is an instance of Command and has
     *         the same command string as this Command object, false otherwise.
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
