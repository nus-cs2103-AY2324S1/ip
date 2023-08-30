package rayshawn.chatbot.commands;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n" + "Example: " + COMMAND_WORD;
    private static final String EXIT_COMMAND_ACKNOWLEDGEMENT = "Thanks for using your friendly neighbourhood ChatBot";

    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_COMMAND_ACKNOWLEDGEMENT);
    }

    /**
     * Checks if the command is an instance of ByeCommand.
     *
     * @param command Command to be compared with
     * @return true if command is an instance; false otherwise
     */
    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
