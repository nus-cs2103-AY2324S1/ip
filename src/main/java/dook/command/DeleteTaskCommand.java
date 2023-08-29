package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

public class DeleteTaskCommand extends Command{
    private final int index;
    public DeleteTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.printMessage(taskList.deleteTask(index));
    }
}
