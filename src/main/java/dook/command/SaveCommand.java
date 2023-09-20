package dook.command;

import dook.DookException;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;

/**
 * Command for saving the current task list.
 */
public class SaveCommand extends Command {
    /**
     * Saves the given task list to file via the given storage.
     * Displays a confirmation message to the user.
     *
     * @param storage  Given storage.
     * @param taskList Given task list.
     * @param parser
     * @return Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Parser parser) throws DookException {
        return storage.saveTaskList(taskList) + "\n" + storage.saveAliases(parser);
    }

}
