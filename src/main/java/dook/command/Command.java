package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

/**
 * Abstract class used for the Command pattern.
 */
public abstract class Command{
    public boolean isExit = false;
    protected Storage storage;
    protected UiDisplay uiDisplay;
    protected TaskList taskList;

    /**
     * Abstract method that can be called from all Commands.
     * @param storage Given storage.
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    public abstract void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException;

}