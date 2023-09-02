package duke.command;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ByeCommand() {
    }

    @Override
    public String[] execute() {
        return new String[]{"Bye! Hope to see you again soon!"};
    }
}
