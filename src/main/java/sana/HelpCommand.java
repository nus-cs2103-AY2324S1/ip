package sana;

/**
 * Represents a HelpCommand that handles displaying a message for invalid commands.
 *
 * This command is triggered when an invalid command is entered by the user. It displays
 * a message indicating that the entered command is not recognized as a valid command.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand object.
     *
     * @param cmd       The command type (e.g., "todo", "deadline", "event").
     * @param arguments The arguments associated with the command.
     */
    public HelpCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes the help command by printing a message to acknowledge invalid command.
     *
     * @param tasks The task list containing all tasks in the program.
     * @param ui The text user interface handling the command execution.
     * @param storage The storage manager for persisting task data.
     * @throws SanaException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(getCmd() + " is not a valid command!");
    }

    /**
     * Checks if the HelpCommand is an exit command.
     *
     * @return always returns false as HelpCommand is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
