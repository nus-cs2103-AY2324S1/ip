package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

public abstract class Command{
    public boolean isExit = false;
    protected Storage storage;
    protected UiDisplay uiDisplay;
    protected TaskList taskList;
    public abstract void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException;

}