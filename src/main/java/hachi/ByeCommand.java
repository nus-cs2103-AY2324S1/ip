package hachi;

import hachi.exceptions.HachiException;
import hachi.exceptions.TooManyArgumentsException;

/**
 * Represents the "bye" command.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ByeCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        return "Bye. Hope to see you again soon!";
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength > 0) {
            throw new TooManyArgumentsException(COMMAND_WORD, 0, argumentLength);
        }
    }
}
