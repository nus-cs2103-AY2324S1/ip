package duke.commands;

import duke.records.ChatRecord;

public abstract class Command {
    protected ChatRecord chatRecord;
    public abstract void init(ChatRecord chatRecord);
    public abstract String execute();
}
