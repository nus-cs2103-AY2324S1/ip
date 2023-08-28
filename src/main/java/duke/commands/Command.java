package duke.commands;
import duke.Storage;
import duke.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasklist, Storage storage);
}


