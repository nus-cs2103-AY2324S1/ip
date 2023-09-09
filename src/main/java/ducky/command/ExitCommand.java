package ducky.command;

import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;

/**
 * Represents a command that exits Ducky.
 */
public class ExitCommand extends Command {

    /**
     * Construct a command that exits Ducky.
     */
    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints farewell message from user interface.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param ui       UserInterface of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.showFarewell();
        return null;
    }
}
