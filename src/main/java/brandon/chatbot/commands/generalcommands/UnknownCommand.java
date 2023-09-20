package brandon.chatbot.commands.generalcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;

/**
 * Represents commands that the program does not understand.
 */
public class UnknownCommand extends Command {
    private static final String DEFAULT_MESSAGE = "I am not intelligent enough to understand what that means... ㅠㅅㅠ";
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
