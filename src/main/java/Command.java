import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasklst, Ui ui, Storage storage) throws DukeException, IOException;
    public abstract boolean isExit();
}
