package duke;
/**
 * Encapsulates commands.
 */
public abstract class Command {
    public abstract void execute(ChatBotList list, Ui ui, Storage storage) throws ChatBotListException;
}
