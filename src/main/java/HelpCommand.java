/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Let's learn what I understand.\n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE
                        + "\n" + DeleteCommand.MESSAGE_USAGE
                        + "\n" + ListCommand.MESSAGE_USAGE
                        + "\n" + MarkCommand.MESSAGE_USAGE
                        + "\n" + UnmarkCommand.MESSAGE_USAGE
                        + "\n" + HelpCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}