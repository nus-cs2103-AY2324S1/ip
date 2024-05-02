package duke;

/**
 * Executes a specified command.
 */
public abstract class Command {

    /**
     * The command execution line.
     * @return Confirmation that the command is executed.
     */
    public abstract String execute();
}
