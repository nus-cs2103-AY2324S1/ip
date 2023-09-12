package command;

import java.io.IOException;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;

/**
 * Clears the list.
 */
public class ClearExecutable implements Executable {

    /**
     * Clears the list, and produces an output if successful.
     * @param list list to be cleared.
     * @param ui the ui to output to if successful.
     * @return false, since the method does not terminate the bot.
     * @throws FailureInExecuteException if we cannot clear.
     */
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
