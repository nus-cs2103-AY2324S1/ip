package rayshawn.chatbot.commands;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n" + "Example: " + COMMAND_WORD;
    private static final String EXIT_COMMAND_ACKNOWLEDGEMENT = "Thanks for using your friendly neighbourhood ChatBot";

    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_COMMAND_ACKNOWLEDGEMENT);
    }

    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
