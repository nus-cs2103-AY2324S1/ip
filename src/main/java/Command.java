import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}

// ... other command classes for "mark", "unmark", etc.
