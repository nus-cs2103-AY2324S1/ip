package ducky.command;

import ducky.Storage;
import ducky.TaskList;

/**
 * Represents a command that exits Ducky.
 */
public class ExitCommand extends Command {

    /**
     * Construct a command that exits Ducky.
     */
    public ExitCommand() {}

    /**
     * Prints farewell message from user interface.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "";
    }
}
