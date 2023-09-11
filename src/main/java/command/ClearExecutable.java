package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;

/**
 * Clears the list.
 */
public class ClearExecutable implements Executable {

    @Override
    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        try {
            list.clear();
        } catch (IOException e) {
            throw new FailureInExecuteException("Could not clear;" + e.getMessage());
        }
        ui.output("Cleared!");
        return false;
    }
}
