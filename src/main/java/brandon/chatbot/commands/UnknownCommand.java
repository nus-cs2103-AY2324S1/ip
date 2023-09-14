package brandon.chatbot.commands;

/**
 * Represents commands that the program does not understand.
 */
public class UnknownCommand extends Command {
    private static final String DEFAULT_MESSAGE = "I am not intelligent enough to understand what that means...";
    private String feedback;

    public UnknownCommand() {
        this.feedback = DEFAULT_MESSAGE;
    }

    public UnknownCommand(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(this.feedback);
    }
}
