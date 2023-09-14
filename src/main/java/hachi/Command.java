package hachi;

import exceptions.HachiException;

/**
 * Parent command class.
 */
public abstract class Command {

    /**
     * Default command word.
     */
    private static final String COMMAND_WORD = "default_cmd";

    private String[] arguments;

    public Command(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the command.
     * @return The output for the given command.
     */
    public String execute() throws HachiException {
        return "";
    }

    public String[] getArguments() {
        return arguments;
    }

    protected abstract void checkArgumentLength(int argumentLength) throws HachiException;
}
