package duke;
/**
 * Encapsulates commands.
 */
public abstract class Command {
    public abstract String execute(ChatBotList list, Storage storage) throws DukeException;
}
