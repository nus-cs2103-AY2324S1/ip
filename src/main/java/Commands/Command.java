package Commands;
import Utilities.Storage;
import Utilities.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasklist, Storage storage);
}


