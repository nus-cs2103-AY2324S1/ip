package aichan.command;

import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;

/**
 * Represents a command to exit the program.
 * Inherits from the Command class.
 */
public class ExitCommand extends Command{

    /**
     * Says bye to user and exists the program.
     *
     * @param tasks A list of task.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // no interaction with tasks
        ui.showMessage("Ta-da! It's time to go~ Keep smiling till we reunite!");
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
