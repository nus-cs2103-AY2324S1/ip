package bob.command;
import bob.*;

/**
 * Handles the execution of different types of commands.
 */
public abstract class Command {

    public String input;

    public boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}


