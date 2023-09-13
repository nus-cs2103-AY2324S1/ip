package sana;

/**
 * An abstract class representing a command in the Sana application.
 * Commands encapsulate specific actions that can be executed on the application's task list.
 */
public abstract class Command {

    private String command;
    private String arguments;

    /**
     * Constructs a Command object.
     *
     * @param command      The command type (e.g., "todo", "deadline", "event").
     * @param arguments The arguments associated with the command.
     */
    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Executes the command's specific action on the given task list, UI, and storage.
     *
     * @param tasks The task list on which the command's action is performed.
     * @param storage The storage manager for persisting task data.
     * @throws SanaException If an exception specific to the Sana application occurs during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws SanaException;

    /**
     * Checks whether the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();


    /**
     * Retrieves the arguments associated with the command.
     *
     * @return The arguments associated with the command.
     */
    public String getArguments() {
        return arguments;
    }

    /**
     * Retrieves the cmd word associated with the command.
     *
     * @return The cmd word associated with the command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Compares this Command object to another object for equality.
     *
     * The comparison is based on the command words and descriptions.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Command) {
            Command c = (Command) obj;

            if (c == null || this == null) {
                return false;
            }

            return this.command.equals(c.command) && this.arguments.equals(c.arguments);
        }
        return false;
    }
}
