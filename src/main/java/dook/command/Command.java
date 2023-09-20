package dook.command;

import dook.DookException;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;


/**
 * Abstract class used for the Command pattern.
 */
public abstract class Command {
    protected boolean isExit = false;
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Executes the command.
     *
     * @param storage  Given storage.
     * @param taskList Given task list.
     * @param parser Given parser.
     * @return Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    public abstract String execute(Storage storage, TaskList taskList, Parser parser) throws DookException;

}
