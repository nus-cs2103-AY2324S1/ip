package duke;

/**
 * Encapsulates the command for exiting the chat bot.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(ChatBotList list, Storage storage) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
