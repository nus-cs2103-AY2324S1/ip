package duke.commands;

import duke.records.ChatRecord;

/**
 * The abstract class for commands.
 * @author Toh Li Yuan (A0255811H)
 */
public abstract class Command {
    protected ChatRecord chatRecord;
    public abstract void init(ChatRecord chatRecord);
    public abstract String execute();
}
