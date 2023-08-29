package parser.commands;

import records.ChatRecord;
import task.Task;

public abstract class Command {
    protected ChatRecord chatRecord;
    public abstract String execute();
}
