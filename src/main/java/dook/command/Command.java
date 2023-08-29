package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

public abstract class Command {
    protected boolean isExit = false;
    protected Storage storage;
    protected UiDisplay uiDisplay;
    protected TaskList taskList;

    public boolean getIsExit() {
        return this.isExit;
    }

    public abstract void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException;

}
