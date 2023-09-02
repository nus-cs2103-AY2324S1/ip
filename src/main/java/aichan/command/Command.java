package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;

/**
 * Represents a command that can be executed.
 */
public class Command {
    /**
     * Does nothing.
     *
     * @param tasks A list of task.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @throws AiChanException If occurs any error.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        // control the main logic
        // add/delete task + ui shows words
    }

    /**
     * Indicates whether this is an ExitCommand.
     *
     * @return False as it is not an ExitCommand.
     */
    public boolean isExit(){
        return false;
    }
}
