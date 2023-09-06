package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents a command that the user has inputted.
 */
public abstract class Command {

    /** The type of Command */
    protected final CommandType commandType;

    /** The current list of tasks */
    protected final TaskList taskList;

    /** The arguments supplied by the user */
    protected final String args;

    /**
     * Constructs a new Command object.
     *
     * @param commandType The type of Command.
     * @param taskList    The current list of tasks.
     * @param args        The arguments supplied by the user.
     */
    public Command(CommandType commandType, TaskList taskList, String args) {
        this.commandType = commandType;
        this.args = args;
        this.taskList = taskList;
    }

    /**
     * Executes the command.
     */
    public abstract String execute();

    /**
     * Returns the type of Command.
     *
     * @return The type of Command.
     */
    public CommandType getCommandType() {
        return this.commandType;
    }
}
