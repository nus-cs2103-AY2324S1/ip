package dook.command;

import dook.DookException;
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
     * Abstract method that can be called from all Commands.
     * @param storage Given storage.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     * @return  Message to be displayed in GUI.
     * @return  Message to be displayed in GUI.
     */
    public abstract String execute(Storage storage, TaskList taskList) throws DookException;

}
