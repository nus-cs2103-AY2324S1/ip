package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the exit command that the user entered
 */
public class ExitCommand extends Command {

    /**
     * Contructor for exit command
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * End the conversation
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that says goodbye
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
