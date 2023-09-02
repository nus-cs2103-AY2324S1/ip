package bob.command;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Handles the execution of different types of commands.
 */
public abstract class Command {

    protected String input;

    protected boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return this.isExit;
    }
}


