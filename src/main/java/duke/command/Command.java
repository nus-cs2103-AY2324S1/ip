package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.UI;


/**
 * The Command class is an abstract class that
 * encapsulates the working of an executable command.
 */
public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command that the command object holds.
     * @param tasks TaskList of the current tasks.
     * @param ui UI class to handle UI.
     * @param storage A storage object to handle file IO.
     * @throws IOException If file is corrupted.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws IOException;
}
